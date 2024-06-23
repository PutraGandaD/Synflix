package com.putragandad.domain.models.movies

data class TopRated (
    val id: Int,
    val posterPath: String,
    val title : String,
    val backdropPath : String,
    val voteAverage : Double,
)