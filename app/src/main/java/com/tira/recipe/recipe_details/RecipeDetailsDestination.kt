package com.tira.recipe.recipe_details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.tira.recipe.common.model.Recipe

private const val RecipeDetailBaseRoute = "recipe-details"
const val RecipeDetailsDestinationRoute = "$RecipeDetailBaseRoute/{recipe}"
const val RecipeArg = "recipe"

fun NavController.navigateToRecipeDetails(recipe: Recipe) {
    val encodedRecipe = Gson().toJson(recipe)
    navigate("$RecipeDetailBaseRoute/$encodedRecipe")
}

fun NavGraphBuilder.recipeDetailsDestination(
    onBackPress: () -> Unit,
) {
    composable(
        route = RecipeDetailsDestinationRoute,
        arguments = listOf(navArgument(RecipeArg) { type = NavType.StringType } )
    ) { backStackEntry ->
        val encodedRecipe = backStackEntry.arguments?.getString(RecipeArg) ?: ""
        val recipe = Gson().fromJson(encodedRecipe, Recipe::class.java)

        RecipeDetailsScreen(
            recipe = recipe,
            onBackPress = onBackPress,
        )
    }
}
