package com.putragandad.moviedbch5.domain.repositories.source

interface RemoteDataSource {
    suspend fun getMovieNowPlaying()

    suspend fun getMoviePopular()

    suspend fun getMovieTopRated()

    suspend fun getMovieDetails(query: String)

    suspend fun getMovieCredits(query: String)
}