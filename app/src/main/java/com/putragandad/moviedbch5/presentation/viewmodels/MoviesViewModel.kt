package com.putragandad.moviedbch5.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.putragandad.moviedbch5.domain.models.movies.Details
import com.putragandad.moviedbch5.domain.models.movies.NowPlaying
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.domain.usecases.movies.DetailsUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.moviedbch5.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MoviesRepository,
    private val detailsUseCase: DetailsUseCase,
    private val nowPlayingUseCase: NowPlayingUseCase
) : ViewModel() {
    private val _movieDetails = MutableLiveData<Resource<Details>>()
    val movieDetails: LiveData<Resource<Details>> = _movieDetails

    private val _movieNowPlaying = MutableLiveData<Resource<List<NowPlaying>>>()
    val movieNowPlaying: LiveData<Resource<List<NowPlaying>>> = _movieNowPlaying

    init {
        getMovieNowPlaying()
    }

    fun getMovieNowPlaying() {
        viewModelScope.launch {
            nowPlayingUseCase.invoke().collect{ result ->
                _movieNowPlaying.value = result
            }
        }
    }

    fun getMoviePopular() = liveData(Dispatchers.IO) {
        emit(repository.getMoviePopular())
    }

    fun getMovieTopRated() = liveData(Dispatchers.IO) {
        emit(repository.getMovieTopRated())
    }

    fun getMovieDetails(query: String) {
        viewModelScope.launch {
            detailsUseCase.invoke(query).collect { result ->
                _movieDetails.value = result
            }
        }
    }

    fun getMovieCredits(query: String) = liveData(Dispatchers.IO) {
        emit(repository.getMovieCredits(query))
    }
}

