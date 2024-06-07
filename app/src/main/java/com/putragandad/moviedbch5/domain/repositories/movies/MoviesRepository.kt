package com.putragandad.moviedbch5.domain.repositories.movies

import com.putragandad.moviedbch5.data.services.remote.response.details.CreditResponse
import com.putragandad.moviedbch5.data.services.remote.response.details.MovieDetailsResponse
import com.putragandad.moviedbch5.data.services.remote.response.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.data.services.remote.response.popular.PopularResponse
import com.putragandad.moviedbch5.data.services.remote.response.top_rated.TopRatedResponse

interface MoviesRepository {
    suspend fun getMovieNowPlaying() : NowPlayingResponse

    suspend fun getMoviePopular() : PopularResponse

    suspend fun getMovieTopRated() : TopRatedResponse

    suspend fun getMovieDetails(query: String) : MovieDetailsResponse

    suspend fun getMovieCredits(query: String) : CreditResponse
}