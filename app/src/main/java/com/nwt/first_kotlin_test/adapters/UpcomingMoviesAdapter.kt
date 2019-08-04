package com.nwt.first_kotlin_test.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.nwt.first_kotlin_test.delegates.ClickMovieDetail
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.viewholders.UpcomingViewHolder
import com.nwt.first_kotlin_test.vos.MovieVO

class UpcomingMoviesAdapter(context : Context,clickMovieDetail: ClickMovieDetail) : BaseRecyclerAdapter<UpcomingViewHolder,MovieVO>(context){

    var mCLickDetail : ClickMovieDetail = clickMovieDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view : View = layoutInflater.inflate(R.layout.movie_card,parent,false)
        return UpcomingViewHolder(view,mCLickDetail)
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder!!.bind(data!!.get(position))
    }
}