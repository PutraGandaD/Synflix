package com.putragandad.testdomain.data.repository

import com.putragandad.moviedbch5.data.network.ApiService
import com.putragandad.moviedbch5.data.network.response.now_playing.asDomain
import com.putragandad.moviedbch5.domain.models.movies.Details
import com.putragandad.moviedbch5.domain.models.movies.MovieCast
import com.putragandad.moviedbch5.domain.models.movies.NowPlaying
import com.putragandad.moviedbch5.domain.models.movies.Popular
import com.putragandad.moviedbch5.domain.models.movies.TopRated
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository

class FakeMovieRepository(private val apiService: ApiService) : MoviesRepository {
    override suspend fun getMovieNowPlaying(): List<NowPlaying> {
        return apiService.getMovieNowPlaying().results.map { it.asDomain() }
    }

    override suspend fun getMoviePopular(): List<Popular> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieTopRated(): List<TopRated> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetails(query: String): Details {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieCast(query: String): List<MovieCast> {
        TODO("Not yet implemented")
    }
}