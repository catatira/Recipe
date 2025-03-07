package com.tira.recipe

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tira.recipe.home.ui.HomeScreenDestinationRoute
import com.tira.recipe.home.ui.homeDestination
import com.tira.recipe.recipe_details.recipeDetailsDestination

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = HomeScreenDestinationRoute,
    ) {
        homeDestination()
        recipeDetailsDestination()
    }
}
