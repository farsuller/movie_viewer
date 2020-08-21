package com.viewer.movieviewer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.viewer.movieviewer.model.MovieDetails
import com.viewer.movieviewer.repository.MovieDetailsRepository
import com.viewer.movieviewer.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieViewerViewModel  (private val movieRepository: MovieDetailsRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val movieDetails: LiveData<MovieDetails> by lazy {
                movieRepository.fetchMovieDetails(compositeDisposable)
    }
    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getMovieNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }



}