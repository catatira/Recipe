package com.tira.recipe.home.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tira.recipe.R
import com.tira.recipe.ui.components.Header

@Composable
fun FavoritesEmptyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Header(stringResource(R.string.home_empty_favorites_header))
        Text(stringResource(R.string.home_empty_favorites_description))
    }
}
