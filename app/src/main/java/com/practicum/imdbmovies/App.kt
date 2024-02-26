package com.practicum.imdbmovies

import android.app.Application
import com.practicum.imdbmovies.common.di.dataModule
import com.practicum.imdbmovies.common.di.domainModule
import com.practicum.imdbmovies.common.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                viewModule, dataModule, domainModule
            )
        }
    }
}