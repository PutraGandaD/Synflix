package com.putragandad.moviedbch5.di

import com.putragandad.moviedbch5.data.implementation.movies.MoviesRepositoryImpl
import com.putragandad.moviedbch5.data.implementation.users.UserAuthRepositoryImpl
import com.putragandad.moviedbch5.data.source.DataStoreSource
import com.putragandad.moviedbch5.data.source.RemoteDataSource
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.domain.repositories.users.UserAuthRepository
import com.putragandad.moviedbch5.domain.usecases.movies.CastUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.DetailsUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.PopularUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.TopRatedUseCase
import com.putragandad.moviedbch5.domain.usecases.users.CheckLoginUseCase
import com.putragandad.moviedbch5.domain.usecases.users.ReadUserInfoUseCase
import com.putragandad.moviedbch5.domain.usecases.users.SetProfilePictureUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UpdateUserInfoUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UserLoginUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UserLogoutUseCase
import com.putragandad.moviedbch5.domain.usecases.users.UserRegisterUseCase
import com.putragandad.moviedbch5.presentation.viewmodels.MoviesViewModel
import com.putragandad.moviedbch5.presentation.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single { RemoteDataSource(get()) } // Declare Remote Data Source
        single { DataStoreSource(get()) } // Declare Preference Data Store Manager
    }

    val repositoryModule = module {
        factory <MoviesRepository> { MoviesRepositoryImpl(get()) } // Repository
        factory <UserAuthRepository> { UserAuthRepositoryImpl(get()) }
    }

    val viewModelModule = module {
        viewModel { MoviesViewModel(get(), get(), get(), get(), get()) }
        viewModel { UserViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    }

    val useCaseModule = module {
        factory { DetailsUseCase(get()) }
        factory { NowPlayingUseCase(get()) }
        factory { PopularUseCase(get()) }
        factory { TopRatedUseCase(get()) }
        factory { CastUseCase(get()) }

        factory { CheckLoginUseCase(get()) }
        factory { UserLoginUseCase(get()) }
        factory { UserRegisterUseCase(get()) }
        factory { ReadUserInfoUseCase(get()) }
        factory { UserLogoutUseCase(get()) }
        factory { UpdateUserInfoUseCase(get()) }
        factory { SetProfilePictureUseCase(get()) }
    }
}