package com.viewer.movieviewer.base

import android.annotation.SuppressLint
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.viewer.movieviewer.repository.MovieDetailsRepository
import com.viewer.movieviewer.requests.MovieApi
import com.viewer.movieviewer.requests.MovieApiClient
import com.viewer.movieviewer.viewmodels.MovieScheduleViewModel
import com.viewer.movieviewer.viewmodels.MovieViewerViewModel

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    lateinit var viewModelSchedule : MovieScheduleViewModel
    lateinit var viewModelMovieViwer : MovieViewerViewModel

    val apiService : MovieApi = MovieApiClient.getClient()
    lateinit var movieRepository: MovieDetailsRepository

    //this is all are for MovieSeatMapActivity
     lateinit var spinnerScheduleDate: Spinner
     lateinit var populateScheduleDate: ArrayList<String>
     lateinit var adapterDate: ArrayAdapter<String>

     lateinit var spinnerScheduleCinema: Spinner
     lateinit var populateScheduleCinema: ArrayList<String>
     lateinit var adapterCinema: ArrayAdapter<String>

     lateinit var spinnerScheduleTime: Spinner
     lateinit var populateScheduleTime: ArrayList<String>
     lateinit var adapterTime: ArrayAdapter<String>

     lateinit var displayTotal : TextView
     lateinit var displaySeatMap : TextView


}