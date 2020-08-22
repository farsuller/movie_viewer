package com.viewer.movieviewer.ui
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.viewer.movieviewer.R
import com.viewer.movieviewer.base.BaseActivity
import com.viewer.movieviewer.model.MovieDetails
import com.viewer.movieviewer.repository.MovieDetailsRepository
import com.viewer.movieviewer.repository.NetworkState
import com.viewer.movieviewer.util.Constants.Companion.LANDSCAPE_ID
import com.viewer.movieviewer.util.Constants.Companion.PORTRAIT_ID
import com.viewer.movieviewer.util.Constants.Companion.POSTER_BASE_URL
import com.viewer.movieviewer.viewmodels.MovieViewerViewModel
import kotlinx.android.synthetic.main.activity_movie_viewer.*

class MovieViewerActivity : BaseActivity() {

    private lateinit var viewModel : MovieViewerViewModel

    private lateinit var movieCast: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_viewer)

        movieRepository = MovieDetailsRepository(apiService)
        movieCast = ArrayList()

        viewModel = getViewModel()
        viewModel.movieDetails.observe(this, Observer {
            bindUI(it)
        })

        viewModel.networkState.observe(this, Observer {
            network_progress_circular.visibility = if (it == NetworkState.LOADING) View.VISIBLE else View.GONE
            network_text_error.visibility = if (it == NetworkState.ERROR) View.VISIBLE else View.GONE
            network_scroll_view.visibility = if (it == NetworkState.ERROR) View.GONE else View.VISIBLE
        })

        button_seatmap.setOnClickListener{
            startActivity(Intent(this, MovieSeatMapActivity::class.java))
            overridePendingTransition(R.anim.activity_transition_fade_in, R.anim.activity_transition_fade_out)
        }
    }

    fun bindUI(it: MovieDetails){

        if(it.linkName == "ghost-shell") movie_name.text = "Ghost in the Shell" else movie_name.text = it.linkName
        //movie_name.text = it.linkName
        movie_genre.text = it.genre
        movie_advisory.text = it.advisoryRating
        movie_duration.text = it.runtimeMins
        movie_release.text = it.releaseDate
        movie_synopsis.text = it.synopsis

        //PS for the sake on removing brackets and numbers in Casts name and not going to loop in textview
        movie_casts.text = it.cast.toString().replace("[", "").replace("]", "").replace("1","")


        val moviePosterUrlLandscape: String = POSTER_BASE_URL + LANDSCAPE_ID
        val moviePosterUrlPortrait: String = POSTER_BASE_URL + PORTRAIT_ID
        Glide.with(this).load(moviePosterUrlLandscape).into(poster_landscape)
        Glide.with(this).load(moviePosterUrlPortrait).into(poster_portrait)
    }

    private fun getViewModel():MovieViewerViewModel =
         ViewModelProvider(this, object: ViewModelProvider.Factory{
             @Suppress("UNCHECKED_CAST")
            override fun <T: ViewModel?> create(modelClass: Class<T>): T =
                 MovieViewerViewModel(movieRepository) as T
         })[MovieViewerViewModel::class.java]

}