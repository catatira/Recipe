package com.tira.recipe.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(top = 20.dp),
        text = text,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
    )
}