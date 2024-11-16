package com.putragandad.presentation.fragments.home

import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated

data class HomeUiState(
    val isMovieNowPlayingLoading: Boolean = false,
    val isMovieTopRatedLoading: Boolean = false,
    val isMoviePopularLoading: Boolean = false,
    val movieNowPlaying: List<NowPlaying>? = null,
    val movieTopRated: List<TopRated>? = null,
    val moviePopular: List<Popular>? = null,
    val isMovieNowPlayingTimeout: Boolean = false,
    val isMovieTopRatedTimeout: Boolean = false,
    val isMoviePopularTimeout: Boolean = false,
    val isNotOffline: Boolean = true,
    val message: String? = null
)

val HomeUiState.isServerTimeout : Boolean
    get() = movieNowPlaying.isNullOrEmpty() &&
        movieTopRated.isNullOrEmpty() &&
        moviePopular.isNullOrEmpty() &&
        isMovieNowPlayingTimeout &&
        isMoviePopularTimeout &&
        isMovieTopRatedTimeout