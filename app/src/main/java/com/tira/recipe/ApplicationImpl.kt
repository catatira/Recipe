package com.tira.recipe

import android.app.Application
import com.tira.recipe.home.di.homeModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ApplicationImpl : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            modules(
                homeModule,
            )
        }
    }
}