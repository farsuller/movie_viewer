package com.viewer.movieviewer.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.viewer.movieviewer.R
import com.viewer.movieviewer.base.BaseActivity
import com.viewer.movieviewer.model.SeatMapDetails
import com.viewer.movieviewer.repository.MovieDetailsRepository
import com.viewer.movieviewer.util.ZoomableViewGroup
import com.viewer.movieviewer.viewmodels.MovieScheduleViewModel

import kotlinx.android.synthetic.main.activity_movie_seat_map.*

class MovieScheduleSeatsActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    private var selectedSeatsMap:HashMap<String, TextView> = HashMap<String, TextView>()

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

        viewModelSchedule.movieSeatMaps.observe(this, Observer {
            getSeats(it)
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

    private fun getSeats(seatMap: SeatMapDetails) {

        for (i in seatMap.seatmap.indices) {
            val row = LinearLayout(this)
            row.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            val seatsTag = TextView(this)
            val layoutParams = LinearLayout.LayoutParams( 0, LinearLayout.LayoutParams.WRAP_CONTENT ,1f)
            seatsTag.layoutParams = layoutParams

            seatsTag.text = seatMap.seatmap[i][0].take(1)
            seatsTag.textSize = 8f
            seatsTag.gravity = Gravity.CENTER
            seatsTag.id = (i+1)*10
            row.addView(seatsTag)

            for (j in seatMap.seatmap[i].indices) {

                if(!seatMap.seatmap[i][j].contains("(")){

                    val selectSeatTag = View(this)
                    val layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)

                    if((j+1) == (seatMap.seatmap[i].size)){
                        layoutParams.setMargins(2,2,2,2)
                    }
                    else if(!seatMap.seatmap[i][j+1].contains("(")){
                        layoutParams.setMargins(2,2,2,2)
                    }
                    else{
                        layoutParams.setMargins(2,2,9,2)
                    }
                    selectSeatTag.layoutParams = layoutParams

                    if(seatMap.available.seats.contains(seatMap.seatmap[i][j])){
                        selectSeatTag.setBackgroundResource(R.drawable.ic_available_seats)
                        selectSeatTag.setOnClickListener {

                            val drawableAConstantState = ContextCompat.getDrawable(this, R.drawable.ic_available_seats)?.constantState
                            if(it.background.constantState == drawableAConstantState){
                                selectSeatTag.setBackgroundResource(R.drawable.ic_selected_seats)
                                addSeat(selectSeatTag)
                            }
                            else{
                                selectSeatTag.setBackgroundResource(R.drawable.ic_available_seats)
                                deleteSeat(selectSeatTag)
                            }
                        }
                    }
                    else{
                        selectSeatTag.setBackgroundResource(R.drawable.ic_your_seats)
                    }
                    selectSeatTag.tag = seatMap.seatmap[i][j]
                    selectSeatTag.id = j + 1 + ((i+1)*10)
                    row.addView(selectSeatTag)
                }
            }

            val seatTag2 = TextView(this)
            val layoutParams2 = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            seatTag2.layoutParams = layoutParams2
            seatTag2.text = seatMap.seatmap[i][0].take(1)
            seatTag2.textSize = 8f
            seatTag2.gravity = Gravity.CENTER
            seatTag2.id = (seatMap.seatmap[i].size + 1) + ((i+1)*10)
            row.addView(seatTag2)

              seatMap_container.addView(row)
        }
        seatMap_container as ZoomableViewGroup
    }
    private fun addSeat(view: View?){

        val seats = TextView(this)
        val layoutParams = LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(4,2,4,2)
        seats.layoutParams = layoutParams

        seats.text = view!!.tag.toString()
        seats.textSize = 14f
        seats.setTextColor(resources.getColor(R.color.black))
        seats.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        seats.gravity = Gravity.CENTER
        seats.setPadding(4,0,4,0)
        selectedSeats_layout.addView(seats)

        selectedSeatsMap[view.tag.toString()] = seats
    }

    private fun deleteSeat(view: View?){
        selectedSeats_layout.removeView(selectedSeatsMap.get(view!!.tag.toString()))
        selectedSeatsMap.remove(view.tag.toString())
    }
}
