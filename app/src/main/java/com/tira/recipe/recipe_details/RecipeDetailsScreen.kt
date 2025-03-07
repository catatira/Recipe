package com.tira.recipe.recipe_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tira.recipe.ui.components.Header

@Composable
fun RecipeDetailsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            Header("Detail screen")
        }
    }
}