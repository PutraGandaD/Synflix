package com.putragandad.presentation.fragments.details

import com.putragandad.synflix.domain.models.movies.Details
import com.putragandad.synflix.domain.models.movies.MovieCast

data class MovieDetailUiState(
    val isMovieDetailsLoading: Boolean = false,
    val isMovieCastLoading: Boolean = false,
    val movieDetails: Details? = null,
    val movieCast: List<MovieCast>? = null,
    val hasInternetConnection: Boolean = true,
    val message: String? = null
)
