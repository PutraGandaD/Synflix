package com.putragandad.moviedbch5

import android.app.Application
import com.putragandad.moviedbch5.di.AppModule.appModule
import com.putragandad.moviedbch5.di.AppModule.repositoryModule
import com.putragandad.moviedbch5.di.AppModule.viewModelModule
import com.putragandad.moviedbch5.di.NetworkModule.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(networkModule, appModule, repositoryModule, viewModelModule)
        }
    }
}