package com.putragandad.synflix.domain.usecases.movies

import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.repositories.movies.MoviesRepository
import com.putragandad.synflix.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NowPlayingUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke() : Flow<Resource<List<NowPlaying>>> {
        return repository.getMovieNowPlaying()
    }
}