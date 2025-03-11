package com.tira.recipe.home.di

import androidx.room.Room
import com.tira.recipe.BuildConfig
import com.tira.recipe.common.di.DefaultDispatcherProvider
import com.tira.recipe.common.di.DispatcherProvider
import com.tira.recipe.data.api.FakeOpenAiClientImpl
import com.tira.recipe.data.api.OpenAiClient
import com.tira.recipe.data.api.OpenAiClientImpl
import com.tira.recipe.data.database.AppDatabase
import com.tira.recipe.data.database.LocalRecipeDao
import com.tira.recipe.data.repository.GsonRecipeParser
import com.tira.recipe.data.repository.GsonRecipeParserImpl
import com.tira.recipe.data.repository.LocalRecipeRepository
import com.tira.recipe.data.repository.LocalRecipeRepositoryImpl
import com.tira.recipe.data.repository.OpenAiRecipeRepository
import com.tira.recipe.data.repository.OpenAiRecipeRepositoryImpl
import com.tira.recipe.home.ui.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    factory<DispatcherProvider> { DefaultDispatcherProvider() }

    factory<OpenAiClient> { if (BuildConfig.DEBUG) FakeOpenAiClientImpl() else OpenAiClientImpl() }

    factory<GsonRecipeParser> { GsonRecipeParserImpl() }

    factory<OpenAiRecipeRepository> {
        OpenAiRecipeRepositoryImpl(
            openAiClient = get(),
            dispatcherProvider = get(),
            gsonParser = get(),
        )
    }

    single<LocalRecipeDao> {
        Room
            .databaseBuilder(
                context = androidContext(),
                klass = AppDatabase::class.java,
                name = "app-database",
            )
            .build()
            .localRecipeDao()
    }

    factory<LocalRecipeRepository> {
        LocalRecipeRepositoryImpl(
            dispatcherProvider = get(),
            localRecipeDao = get(),
        )
    }

    viewModel<HomeViewModel> {
        HomeViewModel(
            dispatchersProvider = get(),
            openAiRecipeRepository = get(),
            localRecipeRepository = get(),
        )
    }
}