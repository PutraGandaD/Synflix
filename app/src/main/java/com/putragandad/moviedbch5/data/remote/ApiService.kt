package com.putragandad.moviedbch5.data.remote

import com.putragandad.moviedbch5.data.remote.response.details.CreditResponse
import com.putragandad.moviedbch5.data.remote.response.details.MovieDetailsResponse
import com.putragandad.moviedbch5.data.remote.response.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.data.remote.response.popular.PopularResponse
import com.putragandad.moviedbch5.data.remote.response.top_rated.TopRatedResponse
import retrofit2.http.GET
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