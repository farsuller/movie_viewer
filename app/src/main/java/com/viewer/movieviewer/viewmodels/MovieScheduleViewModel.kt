package com.viewer.movieviewer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.viewer.movieviewer.model.ScheduleDetails
import com.viewer.movieviewer.model.SeatMapDetails
import com.viewer.movieviewer.repository.MovieDetailsRepository
import io.reactivex.disposables.CompositeDisposable

class MovieScheduleViewModel (private val movieRepository: MovieDetailsRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieSchedules: LiveData<ScheduleDetails> by lazy {
        movieRepository.fetchMovieSchedules(compositeDisposable)
    }
    val movieSeatMaps: LiveData<SeatMapDetails> by lazy {
        movieRepository.fetchMovieSeatMap(compositeDisposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}