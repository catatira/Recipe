package com.tira.recipe.data.api

import com.tira.recipe.BuildConfig
import com.tira.recipe.data.model.OpenAIRequest
import com.tira.recipe.data.model.OpenAIResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApiService {
    @Headers("Content-Type: application/json")
    @POST("v1/chat/completions")
    suspend fun generateRecipes(@Header("Authorization") apiKey: String = "Bearer ${BuildConfig.OPEN_AI_KEY}", @Body request: OpenAIRequest): OpenAIResponse
}
