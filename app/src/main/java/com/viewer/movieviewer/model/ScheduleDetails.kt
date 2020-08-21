package com.viewer.movieviewer.model


import com.google.gson.annotations.SerializedName

data class ScheduleDetails(
    val cinemas: List<Cinema>,
    val dates: List<Date>,
    val times: List<Time>
)


data class Cinema(
    val cinemas: List<CinemaX>,
    val parent: String
)

data class CinemaX(
    @SerializedName("cinema_id")
    val cinemaId: String,
    val id: String,
    val label: String
)

data class Date(
    val date: String,
    val id: String,
    val label: String
)

data class Time(
    val parent: String,
    val times: List<TimeX>
)

data class TimeX(
    val id: String,
    val label: String,
    @SerializedName("popcorn_label")
    val popcornLabel: String,
    @SerializedName("popcorn_price")
    val popcornPrice: String,
    val price: String,
    @SerializedName("schedule_id")
    val scheduleId: String,
    @SerializedName("seating_type")
    val seatingType: String,
    val variant: Any
)