package com.putragandad.moviedbch5.models.top_rated


import com.google.gson.annotations.SerializedName

data class TopRatedResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TopRatedResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)