package com.putragandad.data.implementation.movies

import com.putragandad.data.network.response.details.asDomain
import com.putragandad.data.network.response.now_playing.asDomain
import com.putragandad.data.network.response.popular.asDomain
import com.putragandad.data.network.response.top_rated.asDomain
import com.putragandad.data.source.RemoteDataSource
import com.putragandad.domain.models.movies.Details
import com.putragandad.domain.models.movies.MovieCast
import com.putragandad.domain.models.movies.NowPlaying
import com.putragandad.domain.models.movies.Popular
import com.putragandad.domain.models.movies.TopRated
import com.putragandad.domain.repositories.movies.MoviesRepository

class MoviesRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MoviesRepository {
    override suspend fun getMovieCast(query: String): List<MovieCast> {
        return remoteDataSource.getMovieCredits(query).cast.map { it.asDomain() }
    }

    override suspend fun getMovieDetails(query: String): Details {
        return remoteDataSource.getMovieDetails(query).asDomain()
    }

    override suspend fun getMovieNowPlaying(): List<NowPlaying> {
        return remoteDataSource.getMovieNowPlaying().results.map { it.asDomain() }
    }

    override suspend fun getMoviePopular(): List<Popular> {
        return remoteDataSource.getMoviePopular().results.map { it.asDomain() }
    }

    override suspend fun getMovieTopRated(): List<TopRated> {
        return remoteDataSource.getMovieTopRated().results.map { it.asDomain() }
    }
}