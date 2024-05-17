package com.putragandad.moviedbch5.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.putragandad.moviedbch5.api.ApiClient
import com.putragandad.moviedbch5.repositories.MoviesRepository
import com.putragandad.moviedbch5.repositories.RemoteDataSource

class MoviesViewModelFactory(val remoteDataSource: RemoteDataSource) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: MoviesViewModelFactory? = null

        fun getInstance(context: Context): MoviesViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: MoviesViewModelFactory(
                    RemoteDataSource(ApiClient.apiClient)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(MoviesRepository(remoteDataSource)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}