package com.tira.recipe.recipe_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val RecipeDetailsDestinationRoute = "recipe-details"

fun NavController.navigateToRecipeDetails() {
    navigate(RecipeDetailsDestinationRoute)
}

fun NavGraphBuilder.recipeDetailsDestination(
    onBackPress: () -> Unit,
) {
    composable(route = RecipeDetailsDestinationRoute) {
        RecipeDetailsScreen(
            onBackPress = onBackPress,
        )
    }
}
