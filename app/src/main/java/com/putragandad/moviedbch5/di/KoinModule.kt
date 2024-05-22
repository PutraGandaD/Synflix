package com.putragandad.moviedbch5.di

import com.putragandad.moviedbch5.api.ApiClient
import com.putragandad.moviedbch5.api.ApiService
import com.putragandad.moviedbch5.helper.PrefDataStoreManager
import com.putragandad.moviedbch5.repositories.MoviesRepository
import com.putragandad.moviedbch5.repositories.RemoteDataSource
import com.putragandad.moviedbch5.ui.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.ui.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {
    val appModule = module {
        single { ApiClient.apiClient } // Declare API Client
        single { RemoteDataSource(get()) } // Declare Remote Data Source
        single { PrefDataStoreManager(get()) } // Declare Preference Data Store Manager
    }

    val repositoryModule = module {
        factory { MoviesRepository(get()) } // Repository
    }

    val viewModelModule = module {
        viewModel { MoviesViewModel(get()) }
        viewModel { UserViewModel(get()) }
    }
}