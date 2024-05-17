package com.putragandad.moviedbch5.repositories

import com.putragandad.moviedbch5.api.ApiService

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getMovieNowPlaying() = apiService.getMovieNowPlaying()

    suspend fun getMoviePopular() = apiService.getMoviePopular()

    suspend fun getMovieTopRated() = apiService.getMovieTopRated()
}