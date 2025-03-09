package com.tira.recipe.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tira.recipe.R

@Composable
fun HomeContent(
    homeUiState: HomeState,
    modifier: Modifier = Modifier,
) {
    when (homeUiState) {
        HomeState.Loading ->
            LoadingScreen(modifier = modifier)

        HomeState.Cleared ->
            RecipeListWithHeader(
                recipeList = generatePreviewRecipeList(), // TODO adapt to real data
                headerText = stringResource(R.string.home_favorites_header),
                modifier = modifier,
            )

        HomeState.SearchResults ->
            RecipeListWithHeader(
                recipeList = generatePreviewRecipeList(), // TODO adapt to real data,
                headerText = stringResource(R.string.home_suggested_recipes_header),
                modifier = modifier,
            )

        HomeState.Error ->
            ErrorScreen(modifier = modifier)
    }
}
