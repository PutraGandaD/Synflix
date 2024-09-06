package com.putragandad.synflix.di

import com.putragandad.presentation.fragments.details.MovieDetailViewModel
import com.putragandad.presentation.fragments.home.HomeViewModel
import com.putragandad.synflix.presentation.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val viewModelModule = module {
        viewModel { HomeViewModel(get(), get(), get(), get()) }
        viewModel { MovieDetailViewModel(get(), get(), get()) }
        viewModel { UserViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    }
}