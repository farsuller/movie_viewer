package com.viewer.movieviewer.repository

import androidx.lifecycle.LiveData
import com.viewer.movieviewer.model.MovieDetails
import com.viewer.movieviewer.model.ScheduleDetails
import com.viewer.movieviewer.model.SeatMapDetails
import com.viewer.movieviewer.requests.MovieApi
import com.viewer.movieviewer.requests.MovieNetworkSource
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository (private val apiService: MovieApi){

    private lateinit var movieNetworkSource : MovieNetworkSource

    fun fetchMovieDetails(compositeDisposable: CompositeDisposable): LiveData<MovieDetails>{
        movieNetworkSource = MovieNetworkSource(apiService, compositeDisposable)
        movieNetworkSource.fetchMovieDetails()

        return movieNetworkSource.provideMovieResponse
    }

    fun getMovieNetworkState() : LiveData<NetworkState>{
        return movieNetworkSource.networkState
    }

    fun fetchMovieSchedules(compositeDisposable: CompositeDisposable) : LiveData<ScheduleDetails>{
        movieNetworkSource = MovieNetworkSource(apiService, compositeDisposable)
        movieNetworkSource.fetchMovieSchedules()

        return movieNetworkSource.provideScheduleResponse
    }
    fun fetchMovieSeatMap(compositeDisposable: CompositeDisposable) : LiveData<SeatMapDetails>{
        movieNetworkSource = MovieNetworkSource(apiService, compositeDisposable)
        movieNetworkSource.fetchSeatMaps()

        return movieNetworkSource.provideSeatMapResponse
    }
}