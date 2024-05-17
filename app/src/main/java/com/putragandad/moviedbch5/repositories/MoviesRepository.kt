package com.putragandad.moviedbch5.repositories

class MoviesRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getMovieNowPlaying() = remoteDataSource.getMovieNowPlaying()

    suspend fun getMoviePopular() = remoteDataSource.getMoviePopular()

    suspend fun getMovieTopRated() = remoteDataSource.getMovieTopRated()
}