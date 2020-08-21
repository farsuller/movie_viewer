package com.viewer.movieviewer.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.viewer.movieviewer.requests.MovieApi
import com.viewer.movieviewer.requests.MovieApiClient

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    val apiService : MovieApi = MovieApiClient.getClient()

}