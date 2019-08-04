package com.nwt.first_kotlin_test.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nwt.first_kotlin_test.Activities.ActorDetailActivity
import com.nwt.first_kotlin_test.adapters.PopularMoviesAdapter
import com.nwt.first_kotlin_test.adapters.UpcomingMoviesAdapter
import com.nwt.first_kotlin_test.delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.ViewState.MainViewState
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.utils.toast
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import com.nwt.first_kotlin_test.delegates.ClickCastDetail
import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieVO
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ClickMovieDetail,ClickCastDetail {

    override fun onTapCast(castVO: CastVO?) {
        val intent  = Intent(this,ActorDetailActivity::class.java)
        intent.putExtra("cast_id",castVO?.castId)
        startActivity(intent)
    }

    var showDialogPopular : Boolean = true

    override fun onTap(movieVO: MovieVO?) {
        //Timber.d("id : %d",movieVO?.movieId)
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie_id", movieVO?.movieId)
        startActivity(intent)
    }

    lateinit var appViewModel: AppViewModel

    //lateinit var loadingDialog : ProgressDialog

    lateinit var popularMoviesAdapter: PopularMoviesAdapter

    lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter

    private lateinit var progressDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this@MainActivity)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        popularMoviesAdapter = PopularMoviesAdapter(this, this)

        upcomingMoviesAdapter = UpcomingMoviesAdapter(this,this)

        appViewModel.viewState.observe(this, Observer {
            render(it)
        })

        popular_movies_rv.setHasFixedSize(true)

        popular_movies_rv.adapter = popularMoviesAdapter

        upcoming_movies_rv.setHasFixedSize(true)

        upcoming_movies_rv.adapter = upcomingMoviesAdapter

    }

    private fun render(state : MainViewState) {
        when (state) {
            is MainViewState.PopularMoviesLoadingState -> renderPopularMovieLoadingState()
            is MainViewState.PopularMovieSuccessState -> renderPopularMovieSuccessState(state)
            is MainViewState.PopularMovieFailState -> renderPopularMovieFailState(state)
        }
    }


    private fun renderPopularMovieLoadingState() {
        showDialogPopular = true
        showLoading()
    }

    private fun renderPopularMovieSuccessState(state: MainViewState.PopularMovieSuccessState) {
        progressDialog.hide()
        popularMoviesAdapter.setNewData(state.list)
        upcomingMoviesAdapter.setNewData(state.upcoing)
    }
    
    private fun renderPopularMovieFailState(state: MainViewState.PopularMovieFailState) {
        progressDialog.hide()
        toast(state.message)
    }

    private fun showLoading(){
            progressDialog.setMessage("Loading...")
            progressDialog.setCancelable(false)
            progressDialog.show()
    }

}
