package com.putragandad.data.network.response.details


import com.google.gson.annotations.SerializedName
import com.putragandad.domain.models.movies.MovieCast

data class Cast(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("character")
    val character: String,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("known_for_department")
    val knownForDepartment: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String?
)

fun Cast.asDomain() = MovieCast (
    name = name,
    profilePath = profilePath
)