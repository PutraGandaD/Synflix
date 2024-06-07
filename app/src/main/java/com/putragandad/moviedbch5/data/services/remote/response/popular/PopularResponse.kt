package com.putragandad.moviedbch5.data.services.remote.response.popular


import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PopularResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)