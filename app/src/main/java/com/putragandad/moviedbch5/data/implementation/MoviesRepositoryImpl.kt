package com.putragandad.moviedbch5.data.implementation

import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository

class MoviesRepositoryImpl(private val remoteDataSourceImpl: RemoteDataSourceImpl) : MoviesRepository  {
    override suspend fun getMovieCredits(query: String) {
        return remoteDataSourceImpl.getMovieCredits(query)
    }

    override suspend fun getMovieDetails(query: String) {
        return remoteDataSourceImpl.getMovieDetails(query)
    }

    override suspend fun getMovieNowPlaying() {
        return remoteDataSourceImpl.getMovieNowPlaying()
    }

    override suspend fun getMoviePopular() {
        return remoteDataSourceImpl.getMoviePopular()
    }

    override suspend fun getMovieTopRated() {
        return remoteDataSourceImpl.getMovieTopRated()
    }
}