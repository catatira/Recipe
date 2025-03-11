package com.tira.recipe.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.home.ui.screens.HomeScreen


const val HomeScreenDestinationRoute = "home"

fun NavGraphBuilder.homeDestination(
    onSelectRecipe: (Recipe) -> Unit,
) {
    composable(route = HomeScreenDestinationRoute) {
        HomeScreen(
            onSelectRecipe = onSelectRecipe,
        )
    }
}
