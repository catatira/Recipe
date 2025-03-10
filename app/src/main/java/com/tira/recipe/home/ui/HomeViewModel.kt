package com.tira.recipe.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.data.repository.OpenAiRecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeState {
    data object Loading : HomeState()
    data object Cleared : HomeState()
    data object SearchResults : HomeState()
    data object Error : HomeState()
}

class HomeViewModel(
    private val dispatchersProvider: DispatcherProvider,
    private val openAiRecipeRepository: OpenAiRecipeRepository,
) : ViewModel() {

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun onInputTextChange(newText: String) {
        viewModelScope.launch(dispatchersProvider.main()) {
            _inputText.emit(newText)
        }
    }

    fun onSearchRecipe() {
        viewModelScope.launch(dispatchersProvider.main()) {
            _homeState.emit(HomeState.Loading)
            val response = openAiRecipeRepository.generateRecipes(_inputText.value)
            _homeState.emit(HomeState.SearchResults)
        }
    }

    fun clearState() {
        viewModelScope.launch(dispatchersProvider.main()) {
            // Pretend to clear state and reload data
            _homeState.emit(HomeState.Loading)
            _inputText.emit("")
            delay(750)
            _homeState.emit(HomeState.Cleared)
        }
    }

    fun refreshSuggestedRecipes() {
        viewModelScope.launch(dispatchersProvider.main()) {
            _homeState.emit(HomeState.Loading)
            val response = openAiRecipeRepository.generateRecipes(_inputText.value)
            _homeState.emit(HomeState.SearchResults)
        }
    }
}
