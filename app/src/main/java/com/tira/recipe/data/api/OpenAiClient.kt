package com.tira.recipe.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface OpenAiClient {
    val apiService: OpenAiApiService
}

class OpenAiClientImpl : OpenAiClient {
    private val baseUrl = "https://api.openai.com/"

    override val apiService: OpenAiApiService by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenAiApiService::class.java)
    }
}
