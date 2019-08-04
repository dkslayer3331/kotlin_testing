package com.nwt.first_kotlin_test.Activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.ViewState.CastDetailViewState
import com.nwt.first_kotlin_test.activities.MovieDetailActivity
import com.nwt.first_kotlin_test.adapters.PopularMoviesAdapter
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import com.nwt.first_kotlin_test.delegates.ClickCastDetail
import com.nwt.first_kotlin_test.delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.utils.toast
import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieVO
import kotlinx.android.synthetic.main.activity_actor_detail.*

class ActorDetailActivity : AppCompatActivity(),ClickMovieDetail,ClickCastDetail{

    override fun onTapCast(castVO: CastVO?) {
        val intent  = Intent(this,ActorDetailActivity::class.java)
        intent.putExtra("cast_id",castVO?.castId)
        startActivity(intent)
    }

    lateinit var appViewModel : AppViewModel

    lateinit var movieAdapter : PopularMoviesAdapter

    lateinit var movie_rv : RecyclerView

    lateinit var dialog : ProgressDialog

    override fun onTap(movieVO: MovieVO?) {
        val intent = Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("movie_id",movieVO?.movieId)
        startActivity(intent)
    }

    fun setupView(castVO: CastVO?){

        Glide.with(this@ActorDetailActivity)
            .load(castVO?.profilePath)
            .into(actor_profile)

        actor_name.text = castVO?.name

        movieAdapter.setNewData(castVO?.movie_credits?.known_movies)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_detail)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

       val castId = intent.getLongExtra("cast_id",0)

        appViewModel.getCastDetail(castId) // call to obseve livedata state

        appViewModel.castDetailViewState.observe(this, Observer {
            render(it)
        })

        movieAdapter = PopularMoviesAdapter(this,this)

        movie_rv.adapter = movieAdapter

    }

    private fun render(it: CastDetailViewState?) {
        when(it){
          is CastDetailViewState.CastDetailViewStateLoading -> showLoading()
          is CastDetailViewState.CastDetailViewStateFail -> showErrorMessage(it)
          is CastDetailViewState.CastDetailViewStateSuccess -> showDetail(it)
        }
    }

    private fun showDetail(state : CastDetailViewState.CastDetailViewStateSuccess){
        dialog.hide()
        setupView(state.castVO)
    }

    private fun showErrorMessage(state: CastDetailViewState.CastDetailViewStateFail){
        dialog.hide()
       toast(state.message)
    }

    private fun showLoading() {
        dialog.setMessage("Loading...")
        dialog.setCancelable(false)
        dialog.show()
    }

}
