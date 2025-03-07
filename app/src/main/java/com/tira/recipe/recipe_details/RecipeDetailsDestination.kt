package com.tira.recipe.recipe_details

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val RecipeDetailsDestinationRoute = "recipe-details"

fun NavGraphBuilder.recipeDetailsDestination() {
    composable(route = RecipeDetailsDestinationRoute) {
        RecipeDetailsScreen()
    }
}
