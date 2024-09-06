package com.putragandad.synflix.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putragandad.common.utils.network.ConnectivityManager
import com.putragandad.presentation.fragments.home.HomeUiState
import com.putragandad.synflix.domain.models.movies.Details
import com.putragandad.synflix.domain.models.movies.MovieCast
import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated
import com.putragandad.synflix.domain.usecases.movies.CastUseCase
import com.putragandad.synflix.domain.usecases.movies.DetailsUseCase
import com.putragandad.synflix.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.synflix.domain.usecases.movies.PopularUseCase
import com.putragandad.synflix.domain.usecases.movies.TopRatedUseCase
import com.putragandad.synflix.common.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val connectivityManager: ConnectivityManager,
    private val detailsUseCase: DetailsUseCase,
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val popularUseCase: PopularUseCase,
    private val topRatedUseCase: TopRatedUseCase,
    private val castUseCase: CastUseCase
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    private val _movieDetails = MutableLiveData<Resource<Details>>()
    val movieDetails: LiveData<Resource<Details>> = _movieDetails

    private val _movieCast = MutableLiveData<Resource<List<MovieCast>>>()
    val movieCast : LiveData<Resource<List<MovieCast>>> = _movieCast

    init {
        initializeHomeScreen()
    }

    fun initializeHomeScreen() = viewModelScope.launch {
        if(connectivityManager.hasInternetConnection()) {
            hasInternetConnection()

            val movieNowPlaying = nowPlayingUseCase.invoke()
            val moviePopular = popularUseCase.invoke()
            val movieTopRated = topRatedUseCase.invoke()

            combine(movieNowPlaying, moviePopular, movieTopRated) { nowPlaying, popular, topRated ->
                when (nowPlaying) {
                    is Resource.Success -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                movieNowPlayingLoading = false,
                                movieNowPlaying = nowPlaying.data ?: emptyList(),
                                message = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                movieNowPlayingLoading = false,
                                movieNowPlaying = emptyList(),
                                message = nowPlaying.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                movieNowPlayingLoading = true,
                                movieNowPlaying = emptyList(),
                                message = null
                            )
                        }
                    }
                }

                when(popular) {
                    is Resource.Success -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                moviePopularLoading = false,
                                moviePopular = popular.data ?: emptyList(),
                                message = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                moviePopularLoading = false,
                                moviePopular = emptyList(),
                                message = popular.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                moviePopularLoading = true,
                                moviePopular = emptyList(),
                                message = null
                            )
                        }
                    }
                }

                when(topRated) {
                    is Resource.Success -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                movieTopRatedLoading = false,
                                movieTopRated = topRated.data ?: emptyList(),
                                message = null)
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                movieTopRatedLoading = false,
                                movieTopRated = emptyList(),
                                message = topRated.message)
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                movieTopRatedLoading = true,
                                movieTopRated = emptyList(),
                                message = null)
                        }
                    }
                }
            }.collect()
        } else {
            _homeUiState.update { currentUiState ->
                currentUiState.copy(message = "No Internet Connection", hasInternetConnection = false)
            }
        }
    }

    fun messageShowed() = viewModelScope.launch {
        _homeUiState.update { currentUiState ->
            currentUiState.copy(message = null)
        }
    }

    private fun hasInternetConnection() = viewModelScope.launch {
        _homeUiState.update { currentUiState ->
            currentUiState.copy(hasInternetConnection = true)
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

