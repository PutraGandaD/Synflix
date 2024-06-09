package com.putragandad.moviedbch5.domain.repositories.movies

import com.putragandad.moviedbch5.data.services.remote.response.details.CreditResponse
import com.putragandad.moviedbch5.data.services.remote.response.details.MovieDetailsResponse
import com.putragandad.moviedbch5.data.services.remote.response.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.data.services.remote.response.popular.PopularResponse
import com.putragandad.moviedbch5.data.services.remote.response.top_rated.TopRatedResponse
import com.putragandad.moviedbch5.domain.models.movies.Details
import com.putragandad.moviedbch5.domain.models.movies.MovieCast
import com.putragandad.moviedbch5.domain.models.movies.NowPlaying
import com.putragandad.moviedbch5.domain.models.movies.Popular
import com.putragandad.moviedbch5.domain.models.movies.TopRated

interface MoviesRepository {
    suspend fun getMovieNowPlaying() : List<NowPlaying>

    suspend fun getMoviePopular() : List<Popular>

    suspend fun getMovieTopRated() : List<TopRated>

    suspend fun getMovieDetails(query: String) : Details

    suspend fun getMovieCast(query: String) : List<MovieCast>
}