package com.putragandad.moviedbch5.api

import com.putragandad.moviedbch5.models.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.models.popular.PopularResponse
import com.putragandad.moviedbch5.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying() : NowPlayingResponse

    @GET("movie/popular")
    suspend fun getMoviePopular() : PopularResponse
}