package com.putragandad.moviedbch5.domain.repositories.movies

import com.putragandad.moviedbch5.domain.repositories.source.RemoteDataSource

interface MoviesRepository {
    suspend fun getMovieNowPlaying()

    suspend fun getMoviePopular()

    suspend fun getMovieTopRated()

    suspend fun getMovieDetails(query: String)

    suspend fun getMovieCredits(query: String)
}