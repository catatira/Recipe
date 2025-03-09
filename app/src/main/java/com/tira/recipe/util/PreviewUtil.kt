package com.tira.recipe.util

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tira.recipe.ui.theme.RecipeTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    RecipeTheme {
        Scaffold(
            modifier = Modifier.wrapContentSize(),
            content = { _ -> content() },
        )
    }
}
