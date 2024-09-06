package com.putragandad.synflix.di

import com.putragandad.common.utils.network.ConnectivityManager
import com.putragandad.synflix.data.implementation.movies.MoviesRepositoryImpl
import com.putragandad.synflix.data.implementation.users.UserAuthRepositoryImpl
import com.putragandad.data.source.datastore.DataStorePreference
import com.putragandad.data.source.local.LocalDataSource
import com.putragandad.data.source.remote.RemoteDataSource
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
        single { DataStorePreference(get()) } // Declare Preference Data Store Manager
        single { LocalDataSource(get()) }
        factory { ConnectivityManager(get()) }
    }
}