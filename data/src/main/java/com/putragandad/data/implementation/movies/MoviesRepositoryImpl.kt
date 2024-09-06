package com.putragandad.synflix.data.implementation.movies

import com.putragandad.synflix.data.network.response.details.asDomain
import com.putragandad.synflix.data.network.response.now_playing.asDomain
import com.putragandad.synflix.data.network.response.popular.asDomain
import com.putragandad.synflix.data.network.response.top_rated.asDomain
import com.putragandad.data.source.remote.RemoteDataSource
import com.putragandad.synflix.common.utils.Resource
import com.putragandad.synflix.domain.models.movies.Details
import com.putragandad.synflix.domain.models.movies.MovieCast
import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val remoteDataSource: RemoteDataSource) : MoviesRepository {
    override suspend fun getMovieCast(query: String): Flow<Resource<List<MovieCast>>> = flow {
        emit(Resource.Loading())
        try {
            val data = remoteDataSource.getMovieCredits(query).cast.map { it.asDomain() }
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage.toString()))
        }
    }

    override suspend fun getMovieDetails(query: String): Flow<Resource<Details>> = flow {
        emit(Resource.Loading())
        try {
            val data = remoteDataSource.getMovieDetails(query).asDomain()
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage.toString()))
        }
    }

    override suspend fun getMovieNowPlaying(): Flow<Resource<List<NowPlaying>>> = flow {
        emit(Resource.Loading())
        try {
            val data = remoteDataSource.getMovieNowPlaying().results.map { it.asDomain() }
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage.toString()))
        }
    }

    override suspend fun getMoviePopular(): Flow<Resource<List<Popular>>> = flow {
        emit(Resource.Loading())
        try {
            val data = remoteDataSource.getMoviePopular().results.map { it.asDomain() }
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage.toString()))
        }
    }

    override suspend fun getMovieTopRated(): Flow<Resource<List<TopRated>>> = flow {
        emit(Resource.Loading())
        try {
            val data = remoteDataSource.getMovieTopRated().results.map { it.asDomain() }
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t.localizedMessage.toString()))
        }
    }
}