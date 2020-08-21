package com.viewer.movieviewer.model


import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("advisory_rating")
    val advisoryRating: String,
    @SerializedName("average_rating")
    val averageRating: Any,
    @SerializedName("canonical_title")
    val canonicalTitle: String,
    val cast: List<String>,
    val genre: String,
    @SerializedName("has_schedules")
    val hasSchedules: Int,
    @SerializedName("is_featured")
    val isFeatured: Int,
    @SerializedName("is_inactive")
    val isInactive: Int,
    @SerializedName("is_showing")
    val isShowing: Int,
    @SerializedName("link_name")
    val linkName: String,
    @SerializedName("movie_id")
    val movieId: String,
    val order: Int,
    val poster: String,
    @SerializedName("poster_landscape")
    val posterLandscape: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime_mins")
    val runtimeMins: String,
    val synopsis: String,
    val theater: String,
    @SerializedName("total_reviews")
    val totalReviews: Any,
    val trailer: String,
    val variants: List<String>,
    @SerializedName("watch_list")
    val watchList: Boolean,
    @SerializedName("your_rating")
    val yourRating: Int
)