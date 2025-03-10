package com.tira.recipe.data.repository

import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.common.model.Recipe
import com.tira.recipe.data.api.OpenAiClient
import com.tira.recipe.data.model.Message
import com.tira.recipe.data.model.OpenAIRequest
import kotlinx.coroutines.withContext

interface OpenAiRecipeRepository {
    suspend fun generateRecipes(query: String): List<Recipe>
}

class OpenAiRecipeRepositoryImpl(
    private val openAiClient: OpenAiClient,
    private val dispatcherProvider: DispatcherProvider,
    private val gsonParser: GsonRecipeParser,
) : OpenAiRecipeRepository {

    override suspend fun generateRecipes(query: String): List<Recipe> = withContext(dispatcherProvider.io()) {
        try {
            val recipeRequest = OpenAIRequest(
                messages = listOf(
                    Message(
                        role = "user",
                        content = "Give me a list of five food recipes based on: ${query}. " +
                                "Write your answer in JSON format with the following fields specification: " +
                                "title (String)," +
                                "duration (String)," +
                                "ingredients (String list) and" +
                                "instructions (String list)."
                    )
                )
            )
            val recipeResponse = openAiClient.apiService.generateRecipes(request = recipeRequest)
            val jsonResponse = recipeResponse.choices.firstOrNull()?.message?.content ?: return@withContext emptyList()
            return@withContext gsonParser.parseRecipes(jsonResponse)
        } catch (ex: Exception) {
            ex.printStackTrace()
            emptyList()
        }
    }
}
