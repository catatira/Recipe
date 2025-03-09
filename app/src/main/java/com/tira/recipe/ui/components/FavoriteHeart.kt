package com.tira.recipe.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.tira.recipe.ui.theme.Gray
import com.tira.recipe.util.PreviewWrapper

@Composable
fun FavoriteHeart(
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: () -> Unit = { },
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            imageVector =
                if (isSelected) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = null,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else Gray
        )
    }
}

@PreviewLightDark
@Composable
private fun FavoriteHeartPreview() {
    PreviewWrapper {
        Row {
            FavoriteHeart(isSelected = true)
            FavoriteHeart(isSelected = false)
        }
    }
}
