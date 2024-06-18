package com.putragandad.moviedbch5.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putragandad.moviedbch5.domain.models.movies.Details
import com.putragandad.moviedbch5.domain.models.movies.MovieCast
import com.putragandad.moviedbch5.domain.models.movies.NowPlaying
import com.putragandad.moviedbch5.domain.models.movies.Popular
import com.putragandad.moviedbch5.domain.models.movies.TopRated
import com.putragandad.moviedbch5.domain.usecases.movies.CastUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.DetailsUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.PopularUseCase
import com.putragandad.moviedbch5.domain.usecases.movies.TopRatedUseCase
import com.putragandad.moviedbch5.utils.Resource
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val detailsUseCase: DetailsUseCase,
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val popularUseCase: PopularUseCase,
    private val topRatedUseCase: TopRatedUseCase,
    private val castUseCase: CastUseCase
) : ViewModel() {
    private val _movieDetails = MutableLiveData<Resource<Details>>()
    val movieDetails: LiveData<Resource<Details>> = _movieDetails

    private val _movieNowPlaying = MutableLiveData<Resource<List<NowPlaying>>>()
    val movieNowPlaying: LiveData<Resource<List<NowPlaying>>> = _movieNowPlaying

    private val _moviePopular = MutableLiveData<Resource<List<Popular>>>()
    val moviePopular : LiveData<Resource<List<Popular>>> = _moviePopular

    private val _movieTopRated = MutableLiveData<Resource<List<TopRated>>>()
    val movieTopRated : LiveData<Resource<List<TopRated>>> = _movieTopRated

    private val _movieCast = MutableLiveData<Resource<List<MovieCast>>>()
    val movieCast : LiveData<Resource<List<MovieCast>>> = _movieCast

    init {
        initializeHomeScreen()
    }

    private fun initializeHomeScreen() {
        getMovieNowPlaying()
        getMoviePopular()
        getMovieTopRated()
    }

    private fun getMovieNowPlaying() {
        viewModelScope.launch {
            nowPlayingUseCase.invoke().collect{ result ->
                _movieNowPlaying.value = result
            }
        }
    }

    private fun getMoviePopular() {
        viewModelScope.launch {
            popularUseCase.invoke().collect{ result ->
                _moviePopular.value = result
            }
        }
    }

    private fun getMovieTopRated() {
        viewModelScope.launch {
            topRatedUseCase.invoke().collect{ result ->
                _movieTopRated.value = result
            }
        }
    }

    fun getMovieDetails(query: String) {
        viewModelScope.launch {
            detailsUseCase.invoke(query).collect { result ->
                _movieDetails.value = result
            }
        }
    }

    fun getMovieCast(query: String) {
        viewModelScope.launch {
            castUseCase.invoke(query).collect{ result ->
                _movieCast.value = result
            }
        }
    }
}

