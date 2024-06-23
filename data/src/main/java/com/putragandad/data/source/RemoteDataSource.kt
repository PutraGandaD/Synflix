package com.putragandad.data.source

import com.putragandad.data.network.ApiService
import com.putragandad.data.network.response.details.CreditResponse
import com.putragandad.data.network.response.details.MovieDetailsResponse
import com.putragandad.data.network.response.now_playing.NowPlayingResponse
import com.putragandad.data.network.response.popular.PopularResponse
import com.putragandad.data.network.response.top_rated.TopRatedResponse

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