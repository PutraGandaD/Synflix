package com.putragandad.moviedbch5.api

import com.putragandad.moviedbch5.models.details.CreditResponse
import com.putragandad.moviedbch5.models.details.MovieDetailsResponse
import com.putragandad.moviedbch5.models.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.models.popular.PopularResponse
import com.putragandad.moviedbch5.models.top_rated.TopRatedResponse
import com.putragandad.moviedbch5.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying() : NowPlayingResponse

    @GET("movie/popular")
    suspend fun getMoviePopular() : PopularResponse

    @GET("movie/top_rated")
    suspend fun getMovieTopRated() : TopRatedResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: String
    ) : MovieDetailsResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") id: String
    ) : CreditResponse
}