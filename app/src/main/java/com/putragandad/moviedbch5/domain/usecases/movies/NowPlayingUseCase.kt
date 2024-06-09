package com.putragandad.moviedbch5.domain.usecases.movies

import com.putragandad.moviedbch5.data.services.remote.response.now_playing.asDomain
import com.putragandad.moviedbch5.domain.models.movies.NowPlaying
import com.putragandad.moviedbch5.domain.repositories.movies.MoviesRepository
import com.putragandad.moviedbch5.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NowPlayingUseCase(private val repository: MoviesRepository) {
    operator fun invoke() : Flow<Resource<List<NowPlaying>>> = flow {
        try {
            emit(Resource.Loading<List<NowPlaying>>())
            val movieResult = repository.getMovieNowPlaying()
            emit(Resource.Success<List<NowPlaying>>(movieResult))
        } catch (e: HttpException) {

        } catch (e: IOException) {

        }
    }
}