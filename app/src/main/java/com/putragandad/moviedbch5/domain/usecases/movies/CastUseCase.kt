package com.putragandad.moviedbch5.domain.usecases.movies

import com.putragandad.moviedbch5.data.services.remote.response.details.asDomain
import com.putragandad.moviedbch5.domain.models.movies.MovieCast
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CastUseCase(private val repository: MoviesRepository) {
    operator fun invoke(query: String) : Flow<Resource<List<MovieCast>>> = flow {
        try {
            emit(Resource.Loading<List<MovieCast>>())
            val castResult = repository.getMovieCredits(query).cast.map { it.asDomain() }
            emit(Resource.Success<List<MovieCast>>(castResult))
        } catch (e: HttpException) {

        } catch (e: IOException) {

        }
    }
}