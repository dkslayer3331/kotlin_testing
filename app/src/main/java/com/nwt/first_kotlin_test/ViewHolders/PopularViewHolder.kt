package com.nwt.first_kotlin_test.ViewHolders

import android.view.View
import com.nwt.first_kotlin_test.Delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.vos.MovieVO

class PopularViewHolder(itemView: View,movieDetail: ClickMovieDetail) : BaseViewHolder<MovieVO>(itemView) {

    var movie : MovieVO? = null

    var clickMovieDetail : ClickMovieDetail = movieDetail

    override fun bind(data: MovieVO) {
       //bindings
        this.movie = data

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        clickMovieDetail.onTap(movie)
    }

}