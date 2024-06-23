package com.putragandad.domain.usecases.movies

import com.putragandad.domain.models.movies.TopRated
import com.putragandad.domain.repositories.movies.MoviesRepository
import com.putragandad.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TopRatedUseCase(private val repository: MoviesRepository) {
    operator fun invoke() : Flow<Resource<List<TopRated>>> = flow {
        try {
            emit(Resource.Loading())
            val movieResult = repository.getMovieTopRated()
            emit(Resource.Success(movieResult))
        } catch (e: HttpException) {

        } catch (e: IOException) {

        }
    }
}