package com.viewer.movieviewer.requests

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viewer.movieviewer.model.MovieDetails
import com.viewer.movieviewer.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieNetworkSource (
    private val apiService: MovieApi,
    private val compositeDisposable: CompositeDisposable) {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState : LiveData<NetworkState>
    get() = _networkState

    private val movieDetailResponse = MutableLiveData<MovieDetails>()

    val provideMovieResponse: LiveData<MovieDetails>
    get() = movieDetailResponse

    fun fetchMovieDetails(){

        _networkState.postValue(NetworkState.LOADING)

        try{
            compositeDisposable.add(
                apiService.getMovieDetails().subscribeOn(Schedulers.io())
                    .subscribe({
                      movieDetailResponse.postValue(it)
                        _networkState.postValue(NetworkState.LOADED)
                    },{

                        _networkState.postValue(NetworkState.ERROR)
                        Log.e("Movie Network Source", it.message.toString())
                    })
            )
        }
        catch (e: Exception){
            Log.e("Movie Network Source", e.toString())
        }
    }
}