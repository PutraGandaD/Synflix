package com.putragandad.moviedbch5.repositories

class MoviesRepository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getMovieNowPlaying() = remoteDataSource.getMovieNowPlaying()
}