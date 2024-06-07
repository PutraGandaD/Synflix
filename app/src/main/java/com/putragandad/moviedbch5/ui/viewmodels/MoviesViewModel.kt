package com.putragandad.moviedbch5.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    fun getMovieNowPlaying() = liveData(Dispatchers.IO) {
        emit(repository.getMovieNowPlaying())
    }

    fun getMoviePopular() = liveData(Dispatchers.IO) {
        emit(repository.getMoviePopular())
    }

    fun getMovieTopRated() = liveData(Dispatchers.IO) {
        emit(repository.getMovieTopRated())
    }

    fun getMovieDetails(query: String) = liveData(Dispatchers.IO) {
        emit(repository.getMovieDetails(query))
    }

    fun getMovieCredits(query: String) = liveData(Dispatchers.IO) {
        emit(repository.getMovieCredits(query))
    }
}

