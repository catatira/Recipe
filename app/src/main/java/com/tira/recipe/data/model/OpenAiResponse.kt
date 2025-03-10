package com.tira.recipe.data.model

data class OpenAIResponse(
    val choices: List<Choice>
)

data class Choice(
    val message: Message
)
