package com.tira.recipe

import android.app.Application
import com.tira.recipe.home.di.homeModule
import com.tira.recipe.recipe_details.di.recipeDetailsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationImpl : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationImpl)
            androidLogger()
            modules(
                homeModule,
                recipeDetailsModule,
            )
        }
    }
}