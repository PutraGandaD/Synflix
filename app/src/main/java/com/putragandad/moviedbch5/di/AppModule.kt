package com.putragandad.moviedbch5.di

import com.putragandad.moviedbch5.data.implementation.movies.MoviesRepositoryImpl
import com.putragandad.moviedbch5.data.implementation.source.RemoteDataSourceImpl
import com.putragandad.moviedbch5.utils.prefdatastore.PrefDataStoreManager
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.domain.usecases.movies.CastUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.DetailsUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.PopularUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.TopRatedUseCase
import com.putragandad.moviedbch5.presentation.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.presentation.viewmodels.UserViewModel
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
        viewModel { MoviesViewModel(get(), get(), get(), get(), get()) }
        viewModel { UserViewModel(get()) }
    }

    val useCaseModule = module {
        factory { DetailsUseCase(get()) }
        factory { NowPlayingUseCase(get()) }
        factory { PopularUseCase(get()) }
        factory { TopRatedUseCase(get()) }
        factory { CastUseCase(get()) }
    }
}