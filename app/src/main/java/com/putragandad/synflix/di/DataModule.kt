package com.putragandad.synflix.di

import com.putragandad.synflix.data.implementation.movies.MoviesRepositoryImpl
import com.putragandad.synflix.data.implementation.users.UserAuthRepositoryImpl
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository
import com.putragandad.synflix.domain.repositories.users.UserAuthRepository
import org.koin.dsl.module

object DataModule {
    val repositoryModule = module {
        factory <MoviesRepository> { MoviesRepositoryImpl(get()) } // Repository
        factory <UserAuthRepository> { UserAuthRepositoryImpl(get()) }
    }
}