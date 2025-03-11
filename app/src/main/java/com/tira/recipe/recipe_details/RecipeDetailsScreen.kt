package com.tira.recipe.recipe_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.FillBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tira.recipe.R
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.ui.components.FavoriteHeart
import com.tira.recipe.ui.components.OrderedList
import com.tira.recipe.ui.components.UnorderedList
import com.tira.recipe.ui.theme.Dimensions.genericPresentationImageHeight
import com.tira.recipe.ui.theme.Dimensions.paddingSmall
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: RecipeDetailsViewModel = koinViewModel(),
    onBackPress: () -> Unit = { },
) {
    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            val recipe by viewModel.recipeFlow.collectAsStateWithLifecycle()

            RecipeDetailsHeader(onBackPress = onBackPress)
            RecipeDetailsTitleSection(recipe = recipe, onFavoriteClick = viewModel::onToggleRecipeFavorite)
            RecipeDetailsInstructionsSection(recipe = recipe)
        }
    }
}

@Composable
private fun RecipeDetailsHeader(
    onBackPress: () -> Unit,
) {
    Box {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            modifier = Modifier
                .fillMaxWidth()
                .height(genericPresentationImageHeight),
            contentScale = FillBounds,
            contentDescription = null,
        )

        IconButton(
            modifier = Modifier.align(Alignment.TopStart),
            onClick = onBackPress,
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }
    }
}

@Composable
private fun RecipeDetailsTitleSection(
    recipe: Recipe,
    onFavoriteClick: () -> Unit,
) {
    Row(modifier = Modifier.padding(paddingSmall + paddingSmall)) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = recipe.title,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(
                text = recipe.duration,
            )
        }
        FavoriteHeart(
            isSelected = recipe.isFavorited,
            onClick = onFavoriteClick,
        )
    }
}

@Composable
private fun RecipeDetailsInstructionsSection(recipe: Recipe) {
    Column(modifier = Modifier.padding(horizontal = paddingSmall + paddingSmall)) {
        Text(
            text = stringResource(R.string.recipe_description_ingredients_header),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
        )
        UnorderedList(items = recipe.ingredients)

        Spacer(modifier = Modifier.size(paddingSmall))

        Text(
            text = stringResource(R.string.recipe_description_instructions_header),
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleMedium,
        )
        OrderedList(items = recipe.instructions)
    }
}
