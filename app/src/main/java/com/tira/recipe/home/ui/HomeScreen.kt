package com.tira.recipe.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tira.recipe.R
import com.tira.recipe.ui.theme.Dimensions.paddingExtraSmall
import com.tira.recipe.ui.theme.Dimensions.paddingSmall
import com.tira.recipe.ui.theme.Dimensions.tvCornerShape
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
) {
    val homeState by viewModel.homeState.collectAsStateWithLifecycle()
    val isSuggestingRecipes: State<Boolean> = remember(homeState) {
        derivedStateOf {
            homeState is HomeState.SearchResults
        }
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            if (isSuggestingRecipes.value) {
            Button(
                onClick = viewModel::regenerateSuggestedRecipes,
                shape = RoundedCornerShape(paddingExtraSmall),
            ) {
                Text(
                    text = stringResource(R.string.home_refresh_suggestions_button_text),
                    modifier = Modifier.padding(
                        horizontal = paddingSmall,
                        vertical = paddingExtraSmall,
                    ),
                )
            }
                }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(paddingSmall),
        ) {
            val inputText by viewModel.inputText.collectAsStateWithLifecycle()
            val focusManager = LocalFocusManager.current
            val onSearchRecipe = {
                focusManager.clearFocus()
                viewModel.onSearchRecipe()
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputText,
                onValueChange = viewModel::onInputTextChange,
                placeholder = { Text(stringResource(R.string.home_input_text_placeholder)) },
                trailingIcon = {
                    IconButton(
                        onClick =
                        if (isSuggestingRecipes.value) viewModel::clearState
                        else onSearchRecipe
                    ) {
                        Icon(
                            modifier = Modifier.padding(horizontal = paddingSmall),
                            imageVector = if (isSuggestingRecipes.value) Icons.Default.Clear else Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                },
                maxLines = 1,
                shape = RoundedCornerShape(tvCornerShape),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                ),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearchRecipe() }
                ),
            )

            HomeContent(homeState)
        }
    }
}
