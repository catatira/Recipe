package com.tira.recipe.home.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.tira.recipe.R
import com.tira.recipe.ui.components.Header
import com.tira.recipe.util.PreviewWrapper

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Header(stringResource(R.string.home_error_header))
        Text(stringResource(R.string.home_error_description))
    }
}

@PreviewLightDark
@Composable
private fun ErrorScreenPreview() {
    PreviewWrapper {
        ErrorScreen()
    }
}
