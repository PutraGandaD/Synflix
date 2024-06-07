package com.putragandad.moviedbch5.data.implementation

import com.putragandad.moviedbch5.data.services.remote.ApiService
import com.putragandad.moviedbch5.data.services.remote.response.details.CreditResponse
import com.putragandad.moviedbch5.data.services.remote.response.details.MovieDetailsResponse
import com.putragandad.moviedbch5.data.services.remote.response.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.data.services.remote.response.popular.PopularResponse
import com.putragandad.moviedbch5.data.services.remote.response.top_rated.TopRatedResponse
import com.putragandad.moviedbch5.domain.repositories.source.RemoteDataSource

class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun getMovieNowPlaying() : NowPlayingResponse {
        return apiService.getMovieNowPlaying()
    }

    override suspend fun getMovieCredits(query: String) : CreditResponse {
        return apiService.getMovieCredits(query)
    }

    override suspend fun getMovieDetails(query: String) : MovieDetailsResponse {
        return apiService.getMovieDetails(query)
    }

    override suspend fun getMoviePopular() : PopularResponse {
        return apiService.getMoviePopular()
    }

    override suspend fun getMovieTopRated() : TopRatedResponse {
        return apiService.getMovieTopRated()
    }

}