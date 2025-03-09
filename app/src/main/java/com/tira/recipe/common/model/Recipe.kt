package com.tira.recipe.common.model

data class Recipe(
    val title: String,
    val description: String,
    val isFavorited: Boolean,
    val duration: String,
)
