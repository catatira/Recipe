package com.tira.recipe.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tira.recipe.common.model.Recipe

interface GsonRecipeParser {
    fun parseRecipes(json: String): List<Recipe>
}

class GsonRecipeParserImpl : GsonRecipeParser {
    override fun parseRecipes(json: String): List<Recipe> {
        return try {
            val listType = object : TypeToken<List<Recipe>>() {}.type
            Gson().fromJson(json, listType) ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
