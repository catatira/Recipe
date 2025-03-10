package com.tira.recipe.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.tira.recipe.ui.theme.Dimensions.paddingSmall

@Composable
fun OrderedList(
    items: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        items.forEachIndexed { index, item ->
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = "${index + 1}.",
                    modifier = Modifier
                        .padding(end = paddingSmall),
                )
                Text(
                    text = item,
                    modifier = Modifier.padding(end = paddingSmall),
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
