package com.putragandad.synflix

import android.app.Application
import com.putragandad.synflix.di.AppModule.appModule
import com.putragandad.synflix.di.AppModule.repositoryModule
import com.putragandad.synflix.di.AppModule.useCaseModule
import com.putragandad.synflix.di.AppModule.viewModelModule
import com.putragandad.synflix.di.NetworkModule.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(networkModule, appModule, repositoryModule, viewModelModule, useCaseModule)
        }
    }
}