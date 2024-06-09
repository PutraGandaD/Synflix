package com.putragandad.moviedbch5.data.implementation.movies

import com.putragandad.moviedbch5.data.services.remote.response.details.CreditResponse
import com.putragandad.moviedbch5.data.services.remote.response.details.MovieDetailsResponse
import com.putragandad.moviedbch5.data.services.remote.response.details.asDomain
import com.putragandad.moviedbch5.data.services.remote.response.now_playing.NowPlayingResponse
import com.putragandad.moviedbch5.data.services.remote.response.now_playing.asDomain
import com.putragandad.moviedbch5.data.services.remote.response.popular.PopularResponse
import com.putragandad.moviedbch5.data.services.remote.response.popular.asDomain
import com.putragandad.moviedbch5.data.services.remote.response.top_rated.TopRatedResponse
import com.putragandad.moviedbch5.data.services.remote.response.top_rated.asDomain
import com.putragandad.moviedbch5.data.source.RemoteDataSource
import com.putragandad.moviedbch5.domain.models.movies.Details
import com.putragandad.moviedbch5.domain.models.movies.MovieCast
import com.putragandad.moviedbch5.domain.models.movies.NowPlaying
import com.putragandad.moviedbch5.domain.models.movies.Popular
import com.putragandad.moviedbch5.domain.models.movies.TopRated
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository

class MoviesRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MoviesRepository  {
    override suspend fun getMovieCast(query: String) : List<MovieCast> {
        val cast = remoteDataSource.getMovieCredits(query).cast.map { it.asDomain() }
        return cast
    }

    override suspend fun getMovieDetails(query: String) : Details {
        val details = remoteDataSource.getMovieDetails(query).asDomain()
        return details
    }

    override suspend fun getMovieNowPlaying() : List<NowPlaying> {
        val nowplaying = remoteDataSource.getMovieNowPlaying().results.map { it.asDomain() }
        return nowplaying
    }

    override suspend fun getMoviePopular() : List<Popular> {
        val popular = remoteDataSource.getMoviePopular().results.map { it.asDomain() }
        return popular
    }

    override suspend fun getMovieTopRated() : List<TopRated> {
        val toprated = remoteDataSource.getMovieTopRated().results.map { it.asDomain() }
        return toprated
    }
}