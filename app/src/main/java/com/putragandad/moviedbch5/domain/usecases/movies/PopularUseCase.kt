package com.putragandad.moviedbch5.domain.usecases.movies

import com.putragandad.moviedbch5.data.services.remote.response.popular.asDomain
import com.putragandad.moviedbch5.domain.models.movies.Popular
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PopularUseCase(private val repository: MoviesRepository) {
    operator fun invoke() : Flow<Resource<List<Popular>>> = flow {
        try {
            emit(Resource.Loading<List<Popular>>())
            val movieResult = repository.getMoviePopular().results.map { it.asDomain() }
            emit(Resource.Success<List<Popular>>(movieResult))
        } catch (e: HttpException) {

        } catch (e: IOException) {

        }
    }
}