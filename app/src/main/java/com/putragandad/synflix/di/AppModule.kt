package com.putragandad.synflix.di

import com.putragandad.synflix.data.implementation.movies.MoviesRepositoryImpl
import com.putragandad.synflix.data.implementation.users.UserAuthRepositoryImpl
import com.putragandad.synflix.data.source.DataStoreSource
import com.putragandad.synflix.data.source.RemoteDataSource
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository
import com.putragandad.synflix.domain.repositories.users.UserAuthRepository
import com.putragandad.synflix.domain.usecases.movies.CastUseCase
import com.putragandad.synflix.domain.usecases.movies.DetailsUseCase
import com.putragandad.synflix.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.synflix.domain.usecases.movies.PopularUseCase
import com.putragandad.synflix.domain.usecases.movies.TopRatedUseCase
import com.putragandad.synflix.domain.usecases.users.CheckLoginUseCase
import com.putragandad.synflix.domain.usecases.users.ReadUserInfoUseCase
import com.putragandad.synflix.domain.usecases.users.SetProfilePictureUseCase
import com.putragandad.synflix.domain.usecases.users.UpdateUserInfoUseCase
import com.putragandad.synflix.domain.usecases.users.UserLoginUseCase
import com.putragandad.synflix.domain.usecases.users.UserLogoutUseCase
import com.putragandad.synflix.domain.usecases.users.UserRegisterUseCase
import com.putragandad.synflix.presentation.viewmodels.MoviesViewModel
import com.putragandad.synflix.presentation.viewmodels.UserViewModel
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