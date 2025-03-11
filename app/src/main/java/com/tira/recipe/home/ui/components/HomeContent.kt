package com.tira.recipe.home.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tira.recipe.R
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.home.ui.HomeState
import com.tira.recipe.home.ui.screens.ErrorScreen
import com.tira.recipe.home.ui.screens.FavoritesEmptyScreen
import com.tira.recipe.home.ui.screens.LoadingScreen

@Composable
fun HomeContent(
    homeUiState: HomeState,
    modifier: Modifier = Modifier,
    onSelectRecipe: (Recipe) -> Unit,
    onFavoriteRecipe: (Recipe) -> Unit,
) {
    when (homeUiState) {
        HomeState.Loading ->
            LoadingScreen(modifier = modifier)

        is HomeState.Cleared -> {
            val isEmpty = remember {
                derivedStateOf {
                    homeUiState.recipes.isEmpty()
                }
            }
            if (isEmpty.value) {
                FavoritesEmptyScreen(modifier = modifier)
            } else {
                RecipeListWithHeader(
                    recipeList = homeUiState.recipes,
                    headerText = stringResource(R.string.home_favorites_header),
                    modifier = modifier,
                    onRecipeClick = onSelectRecipe,
                    onFavoriteRecipe = onFavoriteRecipe,
                )
            }
        }

        is HomeState.SearchResults ->
            RecipeListWithHeader(
                recipeList = homeUiState.recipes,
                headerText = stringResource(R.string.home_suggested_recipes_header),
                modifier = modifier,
                onRecipeClick = onSelectRecipe,
                onFavoriteRecipe = onFavoriteRecipe,
            )

        HomeState.Error ->
            ErrorScreen(modifier = modifier)
    }
}
