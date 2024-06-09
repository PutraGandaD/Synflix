package com.putragandad.moviedbch5.domain.usecases.movies

import com.putragandad.moviedbch5.data.services.remote.response.details.asDomain
import com.putragandad.moviedbch5.domain.models.movies.Details
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class DetailsUseCase(private val repository: MoviesRepository) {
    operator fun invoke(query: String) : Flow<Resource<Details>> = flow {
        try {
            emit(Resource.Loading<Details>())
            val details = repository.getMovieDetails(query)
            emit(Resource.Success<Details>(details))
        } catch (e: HttpException) {
            emit(Resource.Error<Details>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<Details>("Couldn't reach server. Check your internet connection."))
        }
    }
}