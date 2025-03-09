package com.tira.recipe.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.tira.recipe.ui.theme.Dimensions.paddingExtraSmall
import com.tira.recipe.ui.theme.Dimensions.paddingMedium

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(top = paddingMedium, bottom = paddingExtraSmall),
        text = text,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
    )
}