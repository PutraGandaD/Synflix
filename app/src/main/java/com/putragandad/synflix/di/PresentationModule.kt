package com.putragandad.synflix.di

import com.putragandad.synflix.presentation.viewmodels.MoviesViewModel
import com.putragandad.synflix.presentation.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val viewModelModule = module {
        viewModel { MoviesViewModel(get(), get(), get(), get(), get(), get()) }
        viewModel { UserViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    }
}