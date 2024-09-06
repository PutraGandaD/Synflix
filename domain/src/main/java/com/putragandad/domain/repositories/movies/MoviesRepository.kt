package com.putragandad.synflix.domain.repositories.movies

import com.putragandad.synflix.common.utils.Resource
import com.putragandad.synflix.domain.models.movies.Details
import com.putragandad.synflix.domain.models.movies.MovieCast
import com.putragandad.synflix.domain.models.movies.NowPlaying
import com.putragandad.synflix.domain.models.movies.Popular
import com.putragandad.synflix.domain.models.movies.TopRated
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovieNowPlaying() : Flow<Resource<List<NowPlaying>>>

    suspend fun getMoviePopular() : Flow<Resource<List<Popular>>>

    suspend fun getMovieTopRated() : Flow<Resource<List<TopRated>>>

    suspend fun getMovieDetails(query: String) : Flow<Resource<Details>>

    suspend fun getMovieCast(query: String) : Flow<Resource<List<MovieCast>>>
}