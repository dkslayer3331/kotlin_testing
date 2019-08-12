package com.nwt.first_kotlin_test.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.viewState.CastDetailViewState
import com.nwt.first_kotlin_test.adapters.PopularMoviesAdapter
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import com.nwt.first_kotlin_test.delegates.ClickCastDetail
import com.nwt.first_kotlin_test.delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.utils.toast
import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieVO
import kotlinx.android.synthetic.main.activity_actor_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ActorDetailActivity : AppCompatActivity(),ClickMovieDetail{

//    override fun onTapCast(castVO: CastVO?) {
//        val intent  = Intent(this,ActorDetailActivity::class.java)
//        intent.putExtra("cast_id",castVO?.castId)
//        startActivity(intent)
//    }

   // val appViewModel : AppViewModel by viewModel()

    lateinit var appViewModel: AppViewModel

    lateinit var movieAdapter : PopularMoviesAdapter

    lateinit var dialog : ProgressDialog

    override fun onTap(movieVO: MovieVO?) {
        val intent = Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("movie_id",movieVO?.movieId)
        startActivity(intent)
    }

    fun setupView(castVO: CastVO?){

        Glide.with(this@ActorDetailActivity)
            .load("http://image.tmdb.org/t/p/w300"+castVO?.profilePath)
            .into(actor_profile)

        actor_name.text = HtmlCompat.fromHtml(getString(R.string.actor_name,castVO?.name),FROM_HTML_MODE_LEGACY)

        about_actor.text = castVO?.bio

        movieAdapter.setNewData(castVO?.movie_credits?.known_movies)

    }

    override fun onPause() {
        super.onPause()
        if(dialog.isShowing) dialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(dialog.isShowing) dialog.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actor_detail)

        known_for_caption.text = HtmlCompat.fromHtml(getString(R.string.known_for_caption), Html.FROM_HTML_MODE_LEGACY)

        dialog = ProgressDialog(this@ActorDetailActivity)

        appViewModel = getViewModel<AppViewModel>()

       // appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

       var castId = intent.getLongExtra("cast_id",0)

        appViewModel.getCastDetail(castId) // call to obseve livedata state

        appViewModel.castDetailViewState.observe(this, Observer {
            render(it)
        })

        movieAdapter = PopularMoviesAdapter(this,this)

        cast_known_movies_rv.adapter = movieAdapter

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
