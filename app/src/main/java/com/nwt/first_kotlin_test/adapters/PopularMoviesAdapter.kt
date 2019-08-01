package com.nwt.first_kotlin_test.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.nwt.first_kotlin_test.delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.viewholders.PopularViewHolder
import com.nwt.first_kotlin_test.vos.MovieVO

class PopularMoviesAdapter(context: Context,clickMovieDetail: ClickMovieDetail) : BaseRecyclerAdapter<PopularViewHolder, MovieVO>(context) {

    val mClickMovieDetail : ClickMovieDetail = clickMovieDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view : View = layoutInflater.inflate(R.layout.movie_card,parent,false)
        return PopularViewHolder(view, mClickMovieDetail)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
      holder!!.bind(data!!.get(position))
    }
}