package com.nwt.first_kotlin_test.ViewHolders

import android.view.View
import com.bumptech.glide.Glide
import com.nwt.first_kotlin_test.Delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.vos.MovieVO
import kotlinx.android.synthetic.main.movie_card.view.*

class PopularViewHolder(itemView: View,movieDetail: ClickMovieDetail) : BaseViewHolder<MovieVO>(itemView) {

    var movie : MovieVO? = null

    var clickMovieDetail : ClickMovieDetail = movieDetail

    override fun bind(data: MovieVO) {
       //bindings
        this.movie = data

        itemView.movie_title.text = data.title

        Glide.with(itemView.context)
            .load("https://image.tmdb.org/t/p/w300"+data.imgUrl)
            .placeholder(R.drawable.digimon)
            .into(itemView.movie_poster)

        itemView.release_year.text  = data.release_date

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        clickMovieDetail.onTap(movie)
    }

}