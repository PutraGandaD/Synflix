package com.putragandad.moviedbch5

import android.app.Application
import com.putragandad.moviedbch5.di.KoinModule.appModule
import com.putragandad.moviedbch5.di.KoinModule.networkModule
import com.putragandad.moviedbch5.di.KoinModule.repositoryModule
import com.putragandad.moviedbch5.di.KoinModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(appModule, repositoryModule, viewModelModule, networkModule))
        }
    }
}