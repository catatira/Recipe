package com.tira.recipe.recipe_details.di

import com.tira.recipe.recipe_details.RecipeDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val recipeDetailsModule =
    module {
        viewModel { params ->
            RecipeDetailsViewModel(
                recipe = params.get(),
                dispatcherProvider = get(),
                localRecipeRepository = get(),
            )
        }
    }
