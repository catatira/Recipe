package com.tira.recipe.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class HomeState {
    data object Loading : HomeState()
    data object Cleared : HomeState()
    data object SearchResults : HomeState()
    data object Error : HomeState()
}

// TODO Inject dispatchers
class HomeViewModel : ViewModel() {

    private val _inputText = MutableStateFlow("")
    val inputText: StateFlow<String> = _inputText.asStateFlow()

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun onInputTextChange(newText: String) {
        _inputText.value = newText
    }

    init {
        viewModelScope.launch {
            _homeState.value = HomeState.SearchResults
        }
    }

    fun onSearchRecipe() {

    }

    fun clearState() {

    }

    fun regenerateSuggestedRecipes() {

    }
}
