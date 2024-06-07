package com.putragandad.moviedbch5.data.services.remote.response.now_playing


import com.google.gson.annotations.SerializedName

data class NowPlayingDates(
    @SerializedName("maximum")
    val maximum: String,
    @SerializedName("minimum")
    val minimum: String
)