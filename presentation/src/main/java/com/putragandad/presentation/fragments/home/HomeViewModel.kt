package com.putragandad.presentation.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putragandad.common.utils.network.ConnectivityManager
import com.putragandad.synflix.common.utils.Resource
import com.putragandad.synflix.domain.usecases.movies.NowPlayingUseCase
import com.putragandad.synflix.domain.usecases.movies.PopularUseCase
import com.putragandad.synflix.domain.usecases.movies.TopRatedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val connectivityManager: ConnectivityManager,
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val popularUseCase: PopularUseCase,
    private val topRatedUseCase: TopRatedUseCase
) : ViewModel() {
    private val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        initializeHomeScreen()
    }

    fun initializeHomeScreen() = viewModelScope.launch {
        if (connectivityManager.hasInternetConnection()) {
            hasInternetConnection()

            val movieNowPlaying = nowPlayingUseCase.invoke()
            val moviePopular = popularUseCase.invoke()
            val movieTopRated = topRatedUseCase.invoke()

            combine(movieNowPlaying, moviePopular, movieTopRated) { nowPlaying, popular, topRated ->
                when (nowPlaying) {
                    is Resource.Success -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieNowPlayingLoading = false,
                                movieNowPlaying = nowPlaying.data ?: emptyList(),
                                message = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieNowPlayingLoading = false,
                                movieNowPlaying = emptyList(),
                                message = nowPlaying.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieNowPlayingLoading = true,
                                movieNowPlaying = emptyList(),
                                message = null
                            )
                        }
                    }
                }

                when (popular) {
                    is Resource.Success -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMoviePopularLoading = false,
                                moviePopular = popular.data ?: emptyList(),
                                message = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMoviePopularLoading = false,
                                moviePopular = emptyList(),
                                message = popular.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMoviePopularLoading = true,
                                moviePopular = emptyList(),
                                message = null
                            )
                        }
                    }
                }

                when (topRated) {
                    is Resource.Success -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieTopRatedLoading = false,
                                movieTopRated = topRated.data ?: emptyList(),
                                message = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieTopRatedLoading = false,
                                movieTopRated = emptyList(),
                                message = topRated.message
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieTopRatedLoading = true,
                                movieTopRated = emptyList(),
                                message = null
                            )
                        }
                    }
                }
            }.collect()
        } else {
            _homeUiState.update { currentUiState ->
                currentUiState.copy(
                    message = "No Internet Connection",
                    hasInternetConnection = false
                )
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
}