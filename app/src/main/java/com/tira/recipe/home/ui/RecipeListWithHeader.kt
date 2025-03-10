package com.tira.recipe.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.ui.components.Header
import com.tira.recipe.ui.theme.Dimensions.paddingSmall
import com.tira.recipe.util.PreviewWrapper

@Composable
fun RecipeListWithHeader(
    recipeList: List<Recipe>,
    headerText: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Header(headerText)

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(items = recipeList) { recipe ->
                RecipeCard(
                    recipe = recipe,
                    modifier = Modifier.padding(vertical = paddingSmall),
                )
            }
        }
    }
}

@Preview
@Composable
private fun RecipeListWithHeaderPreview() {
    val recipeList = generatePreviewRecipeList()
    PreviewWrapper {
        RecipeListWithHeader(recipeList = recipeList, headerText = "Favorites")
    }
}

fun generatePreviewRecipeList() = // TODO Make it private
    listOf(
        Recipe("Recipe 1", isFavorited = true, duration = "20 min.", ingredients = listOf("Ingredient 1", "Ingredient 2"), instructions = listOf("Instruction 1", "Instruction 2")),
        Recipe("Recipe 2", isFavorited = true, duration = "20 min.", ingredients = listOf("Ingredient 1", "Ingredient 2"), instructions = listOf("Instruction 1", "Instruction 2")),
        Recipe("Recipe 3", isFavorited = true, duration = "20 min.", ingredients = listOf("Ingredient 1", "Ingredient 2"), instructions = listOf("Instruction 1", "Instruction 2")),
        Recipe("Recipe 4", isFavorited = true, duration = "20 min.", ingredients = listOf("Ingredient 1", "Ingredient 2"), instructions = listOf("Instruction 1", "Instruction 2")),
    )
