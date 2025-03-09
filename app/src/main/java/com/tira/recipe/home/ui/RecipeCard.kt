package com.tira.recipe.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tira.recipe.R
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.ui.components.FavoriteHeart
import com.tira.recipe.ui.theme.Dimensions.generalCardDimension
import com.tira.recipe.ui.theme.Dimensions.paddingSmall
import com.tira.recipe.util.PreviewWrapper

@Composable
fun RecipeCard(
    recipe: Recipe,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(generalCardDimension),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            verticalAlignment = CenterVertically,
        ) {
            Image(painterResource(R.drawable.ic_launcher_background), contentDescription = null)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(paddingSmall)
                    .fillMaxHeight(),
            ) {
                Text(
                    text = recipe.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.size(paddingSmall))
                Text(recipe.duration)
            }
            FavoriteHeart(
                modifier = Modifier.padding(horizontal = paddingSmall),
                isSelected = recipe.isFavorited,
            )
        }
    }
}

@Preview
@Composable
private fun RecipeCardPreview() {
    PreviewWrapper {
        RecipeCard(Recipe("Recipe 1", "", isFavorited = true, duration = "20 min."))
    }
}
