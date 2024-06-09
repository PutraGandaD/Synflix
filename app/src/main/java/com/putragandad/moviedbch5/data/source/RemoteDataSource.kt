package com.putragandad.moviedbch5.data.source

import com.putragandad.moviedbch5.data.services.remote.ApiService
import com.putragandad.moviedbch5.data.services.remote.response.details.CreditResponse
import com.putragandad.moviedbch5.data.services.remote.response.details.MovieDetailsResponse
import com.putragandad.moviedbch5.data.services.remote.response.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.data.services.remote.response.popular.PopularResponse
import com.putragandad.moviedbch5.data.services.remote.response.top_rated.TopRatedResponse

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getMovieNowPlaying() : NowPlayingResponse {
        return apiService.getMovieNowPlaying()
    }

    suspend fun getMovieCredits(query: String) : CreditResponse {
        return apiService.getMovieCredits(query)
    }

    suspend fun getMovieDetails(query: String) : MovieDetailsResponse {
        return apiService.getMovieDetails(query)
    }

    suspend fun getMoviePopular() : PopularResponse {
        return apiService.getMoviePopular()
    }

    suspend fun getMovieTopRated() : TopRatedResponse {
        return apiService.getMovieTopRated()
    }
}