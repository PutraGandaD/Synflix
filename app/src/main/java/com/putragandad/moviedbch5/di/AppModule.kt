package com.putragandad.moviedbch5.di

import com.putragandad.moviedbch5.data.implementation.MoviesRepositoryImpl
import com.putragandad.moviedbch5.data.implementation.RemoteDataSourceImpl
import com.putragandad.moviedbch5.data.services.remote.ApiService
import com.putragandad.moviedbch5.utils.prefdatastore.PrefDataStoreManager
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.domain.repositories.source.RemoteDataSource
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { RemoteDataSourceImpl(get()) } // Declare Remote Data Source
        single { PrefDataStoreManager(get()) } // Declare Preference Data Store Manager
    }

    val repositoryModule = module {
        factory <MoviesRepository> { MoviesRepositoryImpl(get()) } // Repository
    }

    val viewModelModule = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { UserViewModel(get()) }
    }
}