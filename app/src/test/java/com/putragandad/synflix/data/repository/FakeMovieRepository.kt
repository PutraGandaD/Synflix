package com.putragandad.synflix.data.repository

import com.putragandad.synflix.common.utils.Resource
import com.putragandad.synflix.data.network.ApiService
import com.putragandad.synflix.data.network.response.details.asDomain
import com.putragandad.synflix.data.network.response.now_playing.asDomain
import com.putragandad.synflix.domain.models.movies.Details
import com.putragandad.synflix.domain.models.movies.MovieCast
import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMovieRepository(private val apiService: ApiService) : MoviesRepository {
    override suspend fun getMovieNowPlaying(): Flow<Resource<List<NowPlaying>>> = flow {
        emit(Resource.Loading())
        try {
            val data = apiService.getMovieNowPlaying().results.map { it.asDomain() }
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage.toString()))
        }
    }

    override suspend fun getMoviePopular(): Flow<Resource<List<Popular>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieTopRated(): Flow<Resource<List<TopRated>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetails(query: String): Flow<Resource<Details>> = flow {
        emit(Resource.Loading())
        try {
            val data = apiService.getMovieDetails(query).asDomain()
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage.toString()))
        }
    }

    override suspend fun getMovieCast(query: String): Flow<Resource<List<MovieCast>>> {
        TODO("Not yet implemented")
    }
}