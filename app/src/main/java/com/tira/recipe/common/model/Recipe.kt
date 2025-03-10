package com.tira.recipe.common.model

data class Recipe(
    val title: String,
    val isFavorited: Boolean,
    val duration: String,
    val ingredients: List<String>,
    val instructions: List<String>,
)
