package com.putragandad.moviedbch5.data.implementation.movies

import com.putragandad.moviedbch5.data.implementation.source.RemoteDataSourceImpl
import com.putragandad.moviedbch5.data.services.remote.response.details.CreditResponse
import com.putragandad.moviedbch5.data.services.remote.response.details.MovieDetailsResponse
import com.putragandad.moviedbch5.data.services.remote.response.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.data.services.remote.response.popular.PopularResponse
import com.putragandad.moviedbch5.data.services.remote.response.top_rated.TopRatedResponse
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository

class MoviesRepositoryImpl(private val remoteDataSourceImpl: RemoteDataSourceImpl) : MoviesRepository  {
    override suspend fun getMovieCredits(query: String) : CreditResponse {
        return remoteDataSourceImpl.getMovieCredits(query)
    }

    override suspend fun getMovieDetails(query: String) : MovieDetailsResponse {
        return remoteDataSourceImpl.getMovieDetails(query)
    }

    override suspend fun getMovieNowPlaying() : NowPlayingResponse {
        return remoteDataSourceImpl.getMovieNowPlaying()
    }

    override suspend fun getMoviePopular() : PopularResponse {
        return remoteDataSourceImpl.getMoviePopular()
    }

    override suspend fun getMovieTopRated() : TopRatedResponse {
        return remoteDataSourceImpl.getMovieTopRated()
    }
}