package com.tira.recipe.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.data.repository.LocalRecipeRepository
import com.tira.recipe.data.repository.OpenAiRecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class HomeState {
    data object Loading : HomeState()
    data class Cleared(val recipes: List<Recipe>) : HomeState()
    data class SearchResults(val recipes: List<Recipe>) : HomeState()
    data object Error : HomeState()
}

class HomeViewModel(
    private val dispatchersProvider: DispatcherProvider,
    private val openAiRecipeRepository: OpenAiRecipeRepository,
    private val localRecipeRepository: LocalRecipeRepository,
) : ViewModel() {

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    private val favoritedRecipes: StateFlow<List<Recipe>> =
        localRecipeRepository.getRecipes().stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        viewModelScope.launch(dispatchersProvider.default()) {
            delay(1000)
            _homeState.emit(HomeState.Cleared(favoritedRecipes.value))
        }

        viewModelScope.launch(dispatchersProvider.default()) {
            favoritedRecipes.collect {
                adaptUiStateWithNewFavorites(it)
            }
        }
    }

    fun onInputTextChange(newText: String) {
        viewModelScope.launch(dispatchersProvider.default()) {
            _inputText.emit(newText)
        }
    }

    fun onSearchRecipe() {
        viewModelScope.launch(dispatchersProvider.default()) {
            _homeState.emit(HomeState.Loading)
            val response = openAiRecipeRepository.generateRecipes(_inputText.value)
            _homeState.emit(if (response.isNotEmpty()) HomeState.SearchResults(response) else HomeState.Error)
        }
    }

    fun clearState() {
        viewModelScope.launch(dispatchersProvider.default()) {
            // Pretend to clear state and reload data
            _homeState.emit(HomeState.Loading)
            _inputText.emit("")
            delay(750)
            _homeState.emit(HomeState.Cleared(favoritedRecipes.value))
        }
    }

    fun refreshSuggestedRecipes() {
        viewModelScope.launch(dispatchersProvider.default()) {
            _homeState.emit(HomeState.Loading)
            val response = openAiRecipeRepository.generateRecipes(_inputText.value)
            _homeState.emit(if (response.isNotEmpty()) HomeState.SearchResults(response) else HomeState.Error)
        }
    }

    fun toggleRecipeFavoriteSelection(recipe: Recipe) {
        viewModelScope.launch(dispatchersProvider.default()) {
            if (recipe.isFavorited) {
                localRecipeRepository.removeRecipe(recipe)
            } else {
                localRecipeRepository.insertRecipe(recipe.copy(isFavorited = true))
            }
        }
    }

    private fun adaptUiStateWithNewFavorites(recipeList: List<Recipe>) {
        _homeState.update { currentState ->
            when (currentState) {
                HomeState.Error -> currentState
                HomeState.Loading -> currentState
                is HomeState.Cleared -> HomeState.Cleared(recipes = currentState.recipes.updateFavoritesState(recipeList))
                is HomeState.SearchResults -> HomeState.SearchResults(recipes = currentState.recipes.updateFavoritesState(recipeList))
            }
        }
    }

    private fun List<Recipe>.updateFavoritesState(favoriteRecipes: List<Recipe>): List<Recipe> {
        val favoriteRecipesTitles = favoriteRecipes.map { it.title }
        return this.map {
            if (favoriteRecipesTitles.contains(it.title)) {
                it.copy(isFavorited = true)
            } else {
                it.copy(isFavorited = false)
            }
        }
    }
}
