package com.putragandad.moviedbch5.data.implementation

import com.putragandad.moviedbch5.data.services.remote.ApiService
import com.putragandad.moviedbch5.domain.repositories.source.RemoteDataSource

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getMovieNowPlaying() {
        return apiService.getMovieNowPlaying()
    }

    override suspend fun getMovieCredits(query: String) {
        return apiService.getMovieCredits(query)
    }

    override suspend fun getMovieDetails(query: String) {
        return apiService.getMovieDetails(query)
    }

    override suspend fun getMoviePopular() {
        return apiService.getMoviePopular()
    }

    override suspend fun getMovieTopRated() {
        return apiService.getMovieTopRated()
    }

}