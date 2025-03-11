package com.tira.recipe.recipe_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.data.repository.LocalRecipeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    recipe: Recipe,
    private val dispatcherProvider: DispatcherProvider,
    private val localRecipeRepository: LocalRecipeRepository,
) : ViewModel() {

    private val _recipeFlow: MutableStateFlow<Recipe> = MutableStateFlow(recipe)
    val recipeFlow = _recipeFlow.asStateFlow()

    fun onToggleRecipeFavorite() {
        viewModelScope.launch(dispatcherProvider.default()) {
            _recipeFlow.update {
                val newRecipe = it.copy(isFavorited = !it.isFavorited)
                if (newRecipe.isFavorited) {
                    localRecipeRepository.insertRecipe(newRecipe)
                } else {
                    localRecipeRepository.removeRecipe(newRecipe)
                }
                newRecipe
            }
        }
    }
}
