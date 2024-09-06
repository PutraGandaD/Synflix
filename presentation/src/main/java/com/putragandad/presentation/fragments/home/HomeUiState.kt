package com.putragandad.presentation.fragments.home

import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated

data class HomeUiState(
    val movieNowPlayingLoading: Boolean = false,
    val movieTopRatedLoading: Boolean = false,
    val moviePopularLoading: Boolean = false,
    val movieNowPlaying: List<NowPlaying>? = null,
    val movieTopRated: List<TopRated>? = null,
    val moviePopular: List<Popular>? = null,
    val hasInternetConnection: Boolean = true,
    val message: String? = null
)
