package com.putragandad.data.source.remote

import com.putragandad.synflix.data.network.ApiService
import com.putragandad.synflix.data.network.response.details.CreditResponse
import com.putragandad.synflix.data.network.response.details.MovieDetailsResponse
import com.putragandad.synflix.data.network.response.now_playing.NowPlayingResponse
import com.putragandad.synflix.data.network.response.popular.PopularResponse
import com.putragandad.synflix.data.network.response.top_rated.TopRatedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getMovieNowPlaying() : NowPlayingResponse =
        withContext(Dispatchers.IO) {
            apiService.getMovieNowPlaying()
        }

    suspend fun getMovieCredits(query: String) : CreditResponse =
        withContext(Dispatchers.IO) {
            apiService.getMovieCredits(query)
        }

    suspend fun getMovieDetails(query: String) : MovieDetailsResponse =
        withContext(Dispatchers.IO) {
            apiService.getMovieDetails(query)
        }

    suspend fun getMoviePopular() : PopularResponse =
        withContext(Dispatchers.IO) {
            apiService.getMoviePopular()
        }

    suspend fun getMovieTopRated() : TopRatedResponse =
        withContext(Dispatchers.IO) {
            apiService.getMovieTopRated()
        }
}