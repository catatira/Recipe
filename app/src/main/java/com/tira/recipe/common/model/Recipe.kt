package com.tira.recipe.common.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey val title: String,
    val isFavorited: Boolean = false,
    val duration: String,
    val ingredients: List<String>,
    val instructions: List<String>,
)
