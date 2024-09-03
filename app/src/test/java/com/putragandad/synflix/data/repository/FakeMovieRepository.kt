package com.putragandad.synflix.data.repository

import com.putragandad.synflix.data.network.ApiService
import com.putragandad.synflix.data.network.response.details.asDomain
import com.putragandad.synflix.data.network.response.now_playing.asDomain
import com.putragandad.synflix.domain.models.movies.Details
import com.putragandad.synflix.domain.models.movies.MovieCast
import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository

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
        return apiService.getMovieDetails(query).asDomain()
    }

    override suspend fun getMovieCast(query: String): List<MovieCast> {
        TODO("Not yet implemented")
    }
}