package com.viewer.movieviewer.requests

import com.viewer.movieviewer.model.MovieDetails
import com.viewer.movieviewer.model.ScheduleDetails
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Multipart

//http://ec2-52-76-75-52.ap-southeast-1.compute.amazonaws.com/movie.json
//http://ec2-52-76-75-52.ap-southeast-1.compute.amazonaws.com/schedule.json
//http://ec2-52-76-75-52.ap-southeast-1.compute.amazonaws.com/seatmap.json

interface MovieApi {

    @GET("movie.json")
    fun getMovieDetails(): Single<MovieDetails>

    @GET("schedule.json")
    fun getScheduleDetails(): Single<ScheduleDetails>
}