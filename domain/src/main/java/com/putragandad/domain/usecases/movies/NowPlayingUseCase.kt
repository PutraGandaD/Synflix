package com.putragandad.synflix.domain.usecases.movies

import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository
import com.putragandad.synflix.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NowPlayingUseCase(private val repository: MoviesRepository) {
    operator fun invoke() : Flow<Resource<List<NowPlaying>>> = flow {
        try {
            emit(Resource.Loading())
            val movieResult = repository.getMovieNowPlaying()
            if(movieResult.isNotEmpty()) emit(Resource.Success(movieResult))
        } catch (e: HttpException) {
            emit(Resource.Error("API Error. Data can't retrieved from the API", emptyList()))
        } catch (e: IOException) {
            emit(Resource.Error("Error. Connection Failure / Forced Disconnect / Data Corrupt", emptyList()))
        }
    }
}