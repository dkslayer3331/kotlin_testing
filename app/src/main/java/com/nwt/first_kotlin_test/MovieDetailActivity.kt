package com.nwt.first_kotlin_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    fun setupView(movieVO : MovieVO){
        //movie_detail_title.text = movieVO.title
        movie_detail_title.text = HtmlCompat.fromHtml(getString(R.string.movie_title,movieVO.title),FROM_HTML_MODE_LEGACY)

        movie_detail_overview.text = movieVO.overview

        Glide.with(this@MovieDetailActivity)
            .load("https://image.tmdb.org/t/p/w154"+movieVO.imgUrl)
            .into(movie_detail_poster)

        movie_detail_screentime.text = movieVO.runtime.toString()

    }

    lateinit var appViewModel : AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val id : Long = intent.getLongExtra("movie_id",0)

        appViewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

        appViewModel.getMovieDetail(id)?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { it -> setupView(it) }

    }
}
