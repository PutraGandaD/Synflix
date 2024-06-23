package com.putragandad.domain.repositories.movies

import com.putragandad.domain.models.movies.Details
import com.putragandad.domain.models.movies.MovieCast
import com.putragandad.domain.models.movies.NowPlaying
import com.putragandad.domain.models.movies.Popular
import com.putragandad.domain.models.movies.TopRated

interface MoviesRepository {


    suspend fun getMovieNowPlaying() : List<NowPlaying>

    suspend fun getMoviePopular() : List<Popular>

    suspend fun getMovieTopRated() : List<TopRated>

    suspend fun getMovieDetails(query: String) : Details

    suspend fun getMovieCast(query: String) : List<MovieCast>
}