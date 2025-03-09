package com.tira.recipe.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tira.recipe.R
import com.tira.recipe.ui.components.Header
import com.tira.recipe.ui.theme.Dimensions.paddingMedium
import com.tira.recipe.ui.theme.Dimensions.paddingSmall
import com.tira.recipe.ui.theme.Dimensions.tvCornerShape
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
) {
    Scaffold(
        modifier = modifier,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(paddingMedium),
        ) {
            val inputText by viewModel.inputText.collectAsStateWithLifecycle()

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputText,
                onValueChange = viewModel::onInputTextChange,
                placeholder = { Text(stringResource(R.string.home_input_text_placeholder)) },
                trailingIcon = {
                    IconButton(
                        onClick = viewModel::onSearchRecipe,
                    ) {
                        Icon(
                            modifier = Modifier.padding(end = paddingSmall),
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                },
                shape = RoundedCornerShape(tvCornerShape),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                ),
            )
            Header(stringResource(R.string.home_favorites_header))
        }
    }
}
