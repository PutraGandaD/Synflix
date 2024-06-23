package com.putragandad.domain.usecases.movies

import com.putragandad.domain.models.movies.Details
import com.putragandad.domain.repositories.movies.MoviesRepository
import com.putragandad.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DetailsUseCase(private val repository: MoviesRepository) {
    operator fun invoke(query: String) : Flow<Resource<Details>> = flow {
        try {
            emit(Resource.Loading())
            val details = repository.getMovieDetails(query)
            emit(Resource.Success(details))
        } catch (e: HttpException) {
            emit(Resource.Error( "An unexpected error occured (Client Error)"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}