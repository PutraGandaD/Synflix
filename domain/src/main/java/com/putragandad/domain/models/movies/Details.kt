package com.putragandad.synflix.domain.models.movies

data class Details(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String
)