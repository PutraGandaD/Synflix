package com.putragandad.moviedbch5.models.now_playing

import com.google.gson.annotations.SerializedName


data class NowPlayingResponse(
    @SerializedName("dates")
    val dates: NowPlayingDates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<NowPlayingResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)