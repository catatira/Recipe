package com.tira.recipe.home.di

import com.tira.recipe.common.di.DefaultDispatcherProvider
import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.data.api.OpenAiClient
import com.tira.recipe.data.api.OpenAiClientImpl
import com.tira.recipe.data.repository.GsonRecipeParser
import com.tira.recipe.data.repository.GsonRecipeParserImpl
import com.tira.recipe.data.repository.OpenAiRecipeRepository
import com.tira.recipe.data.repository.OpenAiRecipeRepositoryImpl
import com.tira.recipe.home.ui.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory<DispatcherProvider> { DefaultDispatcherProvider() }

    factory<OpenAiClient> { OpenAiClientImpl() }

    factory<GsonRecipeParser> { GsonRecipeParserImpl() }

    factory<OpenAiRecipeRepository> { OpenAiRecipeRepositoryImpl(
        openAiClient = get(),
        dispatcherProvider = get(),
        gsonParser = get(),
    ) }

    viewModel<HomeViewModel> { HomeViewModel(
        dispatchersProvider = get(),
        openAiRecipeRepository = get(),
    ) }
}