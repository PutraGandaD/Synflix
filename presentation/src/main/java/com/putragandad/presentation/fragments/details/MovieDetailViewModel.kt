package com.putragandad.presentation.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putragandad.common.utils.network.ConnectivityManager
import com.putragandad.synflix.common.utils.Resource
import com.putragandad.synflix.domain.usecases.movies.CastUseCase
import com.putragandad.synflix.domain.usecases.movies.DetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val connectivityManager: ConnectivityManager,
    private val detailsUseCase: DetailsUseCase,
    private val castUseCase: CastUseCase
) : ViewModel() {
    private val _detailUiState = MutableStateFlow(MovieDetailUiState())
    val detailUiState = _detailUiState.asStateFlow()

    fun getMovieDetails(query: String) = viewModelScope.launch {
        if(connectivityManager.isNotOffline()) {
            hasInternetConnection()
            val movieDetails = detailsUseCase.invoke(query)
            val movieCast = castUseCase.invoke(query)

            combine(movieDetails, movieCast) { details, cast ->
                when (details) {
                    is Resource.Success -> {
                        _detailUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieDetailsLoading = false,
                                movieDetails = details.data,
                                message = null)
                        }
                    }

                    is Resource.Error -> {
                        _detailUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieDetailsLoading = false,
                                movieDetails = null,
                                message = details.message)
                        }
                    }

                    is Resource.Loading -> {
                        _detailUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieDetailsLoading = true,
                                movieDetails = null,
                                message = details.message)
                        }
                    }
                }

                when (cast) {
                    is Resource.Success -> {
                        _detailUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieCastLoading = false,
                                movieCast = cast.data ?: emptyList(),
                                message = null)
                        }
                    }

                    is Resource.Error -> {
                        _detailUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieCastLoading = false,
                                movieCast = emptyList(),
                                message = cast.message)
                        }
                    }

                    is Resource.Loading -> {
                        _detailUiState.update { currentUiState ->
                            currentUiState.copy(
                                isMovieCastLoading = true,
                                movieCast = emptyList(),
                                message = null)
                        }
                    }
                }
            }.collect()
        } else {
            _detailUiState.update { currentUiState ->
                currentUiState.copy(
                    message = "No Internet Connection",
                    hasInternetConnection = false
                )
            }
        }
    }

    fun messageShowed() = viewModelScope.launch {
        _detailUiState.update { currentUiState ->
            currentUiState.copy(message = null)
        }
    }

    private fun hasInternetConnection() = viewModelScope.launch {
        _detailUiState.update { currentUiState ->
            currentUiState.copy(hasInternetConnection = true)
        }
    }
}