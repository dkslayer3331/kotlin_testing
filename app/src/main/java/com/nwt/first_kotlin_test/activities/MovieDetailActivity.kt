package com.nwt.first_kotlin_test.activities

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nwt.first_kotlin_test.adapters.CastAdapter
import com.nwt.first_kotlin_test.adapters.GenreAdapter
import com.nwt.first_kotlin_test.delegates.ClickCastDetail
import com.nwt.first_kotlin_test.DetailViewState
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.utils.toast
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieVO
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(),ClickCastDetail {

    override fun onTapCast(castVO: CastVO?) {
            
    }

    lateinit var dialog : ProgressDialog

    lateinit var castAdapter: CastAdapter

    lateinit var genreAdapter: GenreAdapter

    fun setupView(movieVO : MovieVO){

        //movie_detail_title.text = movieVO.title
        movie_detail_title.text = HtmlCompat.fromHtml(getString(R.string.movie_title,movieVO.title),FROM_HTML_MODE_LEGACY)

        cast_caption.text = HtmlCompat.fromHtml(getString(R.string.casts_caption),FROM_HTML_MODE_LEGACY)

        movie_detail_overview.text = movieVO.overview

        Glide.with(this@MovieDetailActivity)
            .load("https://image.tmdb.org/t/p/w154"+movieVO.imgUrl)
            .into(movie_detail_poster)

        movie_detail_screentime.text = movieVO.runtime.toString()

        genreAdapter.setNewData(movieVO.genres)

        castAdapter.setNewData(movieVO.credits.cast)

    }

    fun render(state : DetailViewState){
        when(state){
             is DetailViewState.MovieDetailViewStateLoading -> loading()
             is DetailViewState.MovieDetailViewStateSuccess -> success(state)
             is DetailViewState.MovieDetailViewStateFail -> fail(state)
        }
    }

    fun success(state : DetailViewState.MovieDetailViewStateSuccess){
        dialog.hide()
        setupView(state.movieVO)
    }

    fun loading(){
        dialog.setMessage("Loading...")
        dialog.setCancelable(false)
        dialog.show()
    }

    fun fail(state : DetailViewState.MovieDetailViewStateFail){
        dialog.hide()
        toast(state.message)
    }

    lateinit var appViewModel : AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        genreAdapter = GenreAdapter(this)

        castAdapter = CastAdapter(this,this)

        genre_rv.adapter = genreAdapter

        casts_rv.adapter = castAdapter

        dialog = ProgressDialog(this@MovieDetailActivity)

        val id : Long = intent.getLongExtra("movie_id",0)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        appViewModel.getMovieDetail(id)

        appViewModel.detailViewState.observe(this, Observer {
            render(it)
        })

//        appViewModel.getMovieDetail(id)?.subscribeOn(Schedulers.io())
//            ?.observeOn(AndroidSchedulers.mainThread())
//            ?.subscribe { it -> setupView(it) }

    }
}
