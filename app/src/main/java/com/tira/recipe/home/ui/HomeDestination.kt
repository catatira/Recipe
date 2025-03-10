package com.tira.recipe.home.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val HomeScreenDestinationRoute = "home"

fun NavGraphBuilder.homeDestination(
    onSelectRecipe: () -> Unit,
) {
    composable(route = HomeScreenDestinationRoute) {
        HomeScreen(
            onSelectRecipe = onSelectRecipe,
        )
    }
}
