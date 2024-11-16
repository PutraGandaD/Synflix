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
        if (connectivityManager.isNotOffline()) {
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
                                message = null,
                                isMovieNowPlayingTimeout = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieNowPlayingLoading = false,
                                movieNowPlaying = emptyList(),
                                message = "Terjadi kesalahan pada server. Coba lagi dan periksa koneksi internet anda.",
                                isMovieNowPlayingTimeout = true
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieNowPlayingLoading = true,
                                movieNowPlaying = emptyList(),
                                message = null,
                                isMovieNowPlayingTimeout = false
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
                                message = null,
                                isMoviePopularTimeout = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMoviePopularLoading = false,
                                moviePopular = emptyList(),
                                message = "Terjadi kesalahan pada server. Coba lagi dan periksa koneksi internet anda.",
                                isMoviePopularTimeout = true
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMoviePopularLoading = true,
                                moviePopular = emptyList(),
                                message = null,
                                isMoviePopularTimeout = false
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
                                message = null,
                                isMovieTopRatedTimeout = false
                            )
                        }
                    }

                    is Resource.Error -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieTopRatedLoading = false,
                                movieTopRated = emptyList(),
                                message = "Terjadi kesalahan pada server. Coba lagi dan periksa koneksi internet anda.",
                                isMovieTopRatedTimeout = true
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieTopRatedLoading = true,
                                movieTopRated = emptyList(),
                                message = null,
                                isMovieTopRatedTimeout = false
                            )
                        }
                    }
                }
            }.collect()
        } else {
            _homeUiState.update { currentUiState ->
                currentUiState.copy(
                    message = "No Internet Connection",
                    isNotOffline = false
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
            currentUiState.copy(isNotOffline = true)
        }
    }
}