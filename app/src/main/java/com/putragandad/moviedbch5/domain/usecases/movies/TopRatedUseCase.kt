package com.putragandad.moviedbch5.domain.usecases.movies

import com.putragandad.moviedbch5.data.services.remote.response.top_rated.asDomain
import com.putragandad.moviedbch5.domain.models.movies.TopRated
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TopRatedUseCase(private val repository: MoviesRepository) {
    operator fun invoke() : Flow<Resource<List<TopRated>>> = flow {
        try {
            emit(Resource.Loading<List<TopRated>>())
            val movieResult = repository.getMovieTopRated()
            emit(Resource.Success<List<TopRated>>(movieResult))
        } catch (e: HttpException) {

        } catch (e: IOException) {

        }
    }
}