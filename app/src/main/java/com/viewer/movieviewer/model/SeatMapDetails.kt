package com.viewer.movieviewer.model


import com.google.gson.annotations.SerializedName

data class SeatMapDetails(
    val available: Available,
    val seatmap: List<List<String>>
)

data class Available(
    @SerializedName("seat_count")
    val seatCount: Int,
    val seats: List<String>
)