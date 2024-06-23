package com.putragandad.moviedbch5.domain.repositories.movies

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