package com.viewer.movieviewer.ui

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viewer.movieviewer.R
import com.viewer.movieviewer.base.BaseActivity
import com.viewer.movieviewer.repository.MovieDetailsRepository
import com.viewer.movieviewer.viewmodels.MovieScheduleViewModel

class MovieScheduleSeatsActivity : BaseActivity(), AdapterView.OnItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_seat_map)

        movieRepository = MovieDetailsRepository(apiService)
        viewModelSchedule = getViewModel()

        spinnerScheduleDate = findViewById(R.id.schedule_date)
        spinnerScheduleCinema = findViewById(R.id.schedule_cinema)
        spinnerScheduleTime = findViewById(R.id.schedule_time)

        populateScheduleDate = ArrayList()
        populateScheduleCinema = ArrayList()
        populateScheduleTime = ArrayList()

        viewModelSchedule.movieSchedules.observe(this, Observer {
            for(i in 0..1){
                populateScheduleDate.add(it.dates[i].label)
            }
            for(i in 0..3){
                populateScheduleCinema.add(it.cinemas[0].cinemas[i].label)
            }
            for(i in 0..7){
                populateScheduleTime.add(it.times[0].times[i].label)
            }

            adapterDate = ArrayAdapter(applicationContext, R.layout.spinner_text_holder, populateScheduleDate).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            adapterCinema = ArrayAdapter(applicationContext, R.layout.spinner_text_holder, populateScheduleCinema).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            adapterTime = ArrayAdapter(applicationContext, R.layout.spinner_text_holder, populateScheduleTime).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
            spinnerScheduleDate.adapter = adapterDate
            spinnerScheduleCinema.adapter = adapterCinema
            spinnerScheduleTime.adapter = adapterTime
        })

        spinnerScheduleTime.onItemSelectedListener = this
        spinnerScheduleCinema.onItemSelectedListener = this
        spinnerScheduleDate.onItemSelectedListener = this
    }

    private fun getViewModel():MovieScheduleViewModel =
        ViewModelProvider(this, object: ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel?> create(modelClass: Class<T>): T =
                MovieScheduleViewModel(movieRepository) as T
        })[MovieScheduleViewModel::class.java]

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val items: String = parent?.getItemAtPosition(position) as String

        when (parent.id) {
            R.id.schedule_date -> {

            }
            R.id.schedule_cinema -> {

            }
            R.id.schedule_time -> {

            }
        }
    }

}
