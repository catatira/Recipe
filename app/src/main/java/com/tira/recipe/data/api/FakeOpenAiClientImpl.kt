package com.tira.recipe.data.api

import com.tira.recipe.data.model.Choice
import com.tira.recipe.data.model.Message
import com.tira.recipe.data.model.OpenAIRequest
import com.tira.recipe.data.model.OpenAIResponse
import kotlinx.coroutines.delay
import kotlin.random.Random

class FakeOpenAiClientImpl : OpenAiClient {
    override val apiService: OpenAiApiService by lazy {
        object : OpenAiApiService {
            override suspend fun generateRecipes(
                apiKey: String,
                request: OpenAIRequest
            ): OpenAIResponse {
                delay(2_000)
                return OpenAIResponse(
                    choices = listOf(
                        Choice(
                            message =
                            Message(
                                role = "assistant",
                                content = """[
                                    {
                                    "duration":"20 min.",
                                    "ingredients":["Spaghetti","Bolognese Souce"],
                                    "instructions":["Boil pasta","Add souce"],
                                    "title":"Spaghetti Bolognese ${Random.nextInt()}"
                                    },
                                    {
                                    "duration":"20 min.",
                                    "ingredients":["Spaghetti","Bolognese Souce"],
                                    "instructions":["Boil pasta","Add souce"],
                                    "title":"Spaghetti Bolognese ${Random.nextInt()}"
                                    },
                                    {
                                    "duration":"20 min.",
                                    "ingredients":["Spaghetti","Bolognese Souce"],
                                    "instructions":["Boil pasta","Add souce"],
                                    "title":"Spaghetti Bolognese ${Random.nextInt()}"
                                    },
                                    {
                                    "duration":"20 min.",
                                    "ingredients":["Spaghetti","Bolognese Souce"],
                                    "instructions":["Boil pasta","Add souce"],
                                    "title":"Spaghetti Bolognese ${Random.nextInt()}"
                                    },
                                    {
                                    "duration":"20 min.",
                                    "ingredients":["Spaghetti","Bolognese Souce"],
                                    "instructions":["Boil pasta","Add souce"],
                                    "title":"Spaghetti Bolognese ${Random.nextInt()}"
                                    }
                                    ]""".trimMargin()
                            )
                        )
                    )
                )
            }
        }
    }
}
