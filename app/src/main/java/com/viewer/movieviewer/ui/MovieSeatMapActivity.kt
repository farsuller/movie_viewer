package com.viewer.movieviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viewer.movieviewer.R
import com.viewer.movieviewer.base.BaseActivity
import com.viewer.movieviewer.repository.MovieDetailsRepository
import com.viewer.movieviewer.viewmodels.MovieScheduleViewModel
import androidx.lifecycle.Observer
import com.viewer.movieviewer.model.ScheduleDetails
import com.viewer.movieviewer.requests.MovieApi
import com.viewer.movieviewer.requests.MovieApiClient

class MovieSeatMapActivity  : BaseActivity(), AdapterView.OnItemSelectedListener {

    private var arrayAdapterDate: ArrayAdapter<String> ?= null

   // private var scheduleDateList =? null

    private lateinit var viewModel : MovieScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_map)

        movieRepository = MovieDetailsRepository(apiService)
        viewModel = getViewModel()

        var spinnerScheduleDate : Spinner = findViewById(R.id.schedule_date)
//        viewModel.movieSchedules.observe(this, Observer{
//            scheduleDateList = arrayOf(it.dates.toString())
//        })


    //    arrayAdapterDate = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, scheduleDateList)
        spinnerScheduleDate.adapter = arrayAdapterDate
        spinnerScheduleDate.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing Selected", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
       var items: String = parent?.getItemAtPosition(position) as String
        Toast.makeText(applicationContext, "$items", Toast.LENGTH_SHORT).show()
    }


    private fun getViewModel():MovieScheduleViewModel =
        ViewModelProvider(this, object: ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel?> create(modelClass: Class<T>): T =
                MovieScheduleViewModel(movieRepository) as T
        })[MovieScheduleViewModel::class.java]
}