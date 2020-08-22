package com.viewer.movieviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
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


    private lateinit var viewModel : MovieScheduleViewModel

    private lateinit var spinnerScheduleDate: Spinner
    private lateinit var populateScheduleDate: ArrayList<String>
    private lateinit var adapterDate: ArrayAdapter<String>

    private lateinit var spinnerScheduleCinema: Spinner
    private lateinit var populateScheduleCinema: ArrayList<String>
    private lateinit var adapterCinema: ArrayAdapter<String>

    private lateinit var spinnerScheduleTime: Spinner
    private lateinit var populateScheduleTime: ArrayList<String>
    private lateinit var adapterTime: ArrayAdapter<String>

    lateinit var displayText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_map)

        movieRepository = MovieDetailsRepository(apiService)
        viewModel = getViewModel()

        spinnerScheduleDate = findViewById(R.id.schedule_date)
        spinnerScheduleCinema = findViewById(R.id.schedule_cinema)
        spinnerScheduleTime = findViewById(R.id.schedule_time)

        displayText = findViewById(R.id.tv_dates)

        populateScheduleDate = ArrayList()
        populateScheduleCinema = ArrayList()
        populateScheduleTime = ArrayList()

        viewModel.movieSchedules.observe(this, Observer{

            for(i in 0..1){
                populateScheduleDate.add(it.dates[i].label)

            }
            for(i in 0..3){
                populateScheduleCinema.add(it.cinemas[0].cinemas[i].label)
            }
            for(i in 0..7){
                populateScheduleTime.add(it.times[0].times[i].label)
            }

         //   populateScheduleCinema.add(it.cinemas[0].cinemas[3].label)
         //   populateScheduleTime.add(it.times[i].times[i].label)

            adapterDate = ArrayAdapter(application, android.R.layout.simple_spinner_dropdown_item, populateScheduleDate)
            adapterCinema = ArrayAdapter(application, android.R.layout.simple_spinner_dropdown_item, populateScheduleCinema)
            adapterTime = ArrayAdapter(application, android.R.layout.simple_spinner_dropdown_item, populateScheduleTime)
            spinnerScheduleDate.adapter = adapterDate
            spinnerScheduleCinema.adapter = adapterCinema
            spinnerScheduleTime.adapter = adapterTime
        })

        spinnerScheduleDate.onItemSelectedListener = this
        spinnerScheduleCinema.onItemSelectedListener = this
        spinnerScheduleTime.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(applicationContext, "Nothing Selected", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val items: String = parent?.getItemAtPosition(position) as String

        when (parent.id) {
            R.id.schedule_date -> {
                displayText.text = items
            }
            R.id.schedule_cinema -> {
                displayText.text = items
            }
            R.id.schedule_time -> {
                displayText.text = items
            }
        }
    }


    private fun getViewModel():MovieScheduleViewModel =
        ViewModelProvider(this, object: ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel?> create(modelClass: Class<T>): T =
                MovieScheduleViewModel(movieRepository) as T
        })[MovieScheduleViewModel::class.java]
}