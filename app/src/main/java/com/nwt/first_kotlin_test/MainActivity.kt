package com.nwt.first_kotlin_test

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nwt.first_kotlin_test.Adapters.PopularMoviesAdapter
import com.nwt.first_kotlin_test.Delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.Utils.toast
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ClickMovieDetail {

    override fun onTap(movieVO: MovieVO?) {
        //Timber.d("id : %d",movieVO?.movieId)
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie_id", movieVO?.movieId)
        startActivity(intent)
    }

//    fun showLoading(){
//        loadingDialog = ProgressDialog.show(this@MainActivity,"Loading","loading data")
//        loadingDialog.show()
//    }
//
//    fun hideLoading(){
//        loadingDialog.hide()
//    }

    lateinit var appViewModel: AppViewModel

    //lateinit var loadingDialog : ProgressDialog

    lateinit var popularMoviesAdapter: PopularMoviesAdapter

    private lateinit var progressDialog : ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this@MainActivity)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        popularMoviesAdapter = PopularMoviesAdapter(this, this)

        appViewModel.popular()

        appViewModel.viewState.observe(this, Observer {
            render(it)
        })

        popular_movies_rv.setHasFixedSize(true)

        popular_movies_rv.adapter = popularMoviesAdapter

    }

    private fun render(state : MainViewState) {
        when (state) {
            is MainViewState.PopularMoviesLoadingState -> renderPopularMovieLoadingState()
            is MainViewState.PopularMovieSuccessState -> renderPopularMovieSuccessState(state)
            is MainViewState.PopularMovieFailState -> renderPopularMovieFailState(state)
        }
    }

    private fun renderPopularMovieLoadingState() {
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    private fun renderPopularMovieSuccessState(state: MainViewState.PopularMovieSuccessState) {
        progressDialog.hide()
        popularMoviesAdapter.setNewData(state.list)
    }

    private fun renderPopularMovieFailState(state: MainViewState.PopularMovieFailState) {
        progressDialog.hide()
        toast(state.message)
    }

}
