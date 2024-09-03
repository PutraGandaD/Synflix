package com.putragandad.synflix.domain.usecases.movies

import com.putragandad.synflix.domain.models.movies.MovieCast
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository
import com.putragandad.synflix.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class CastUseCase(private val repository: MoviesRepository) {
    operator fun invoke(query: String) : Flow<Resource<List<MovieCast>>> = flow {
        try {
            emit(Resource.Loading())
            val castResult = repository.getMovieCast(query)
            emit(Resource.Success(castResult))
        } catch (e: HttpException) {

        } catch (e: IOException) {

        }
    }
}