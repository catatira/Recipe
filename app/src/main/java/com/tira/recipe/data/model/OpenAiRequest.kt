package com.tira.recipe.data.model

import com.google.gson.annotations.SerializedName

data class OpenAIRequest(
    val model: String = "gpt-4o-mini",
    val messages: List<Message>,
    @SerializedName("max_tokens")
    val maxTokens: Int = 100,
    val temperature: Double = 0.7
)

data class Message(
    val role: String,
    val content: String
)
