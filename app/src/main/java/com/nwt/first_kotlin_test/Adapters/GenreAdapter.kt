package com.nwt.first_kotlin_test.Adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.ViewHolders.GenreViewHolder
import com.nwt.first_kotlin_test.ViewHolders.PopularViewHolder
import com.nwt.first_kotlin_test.vos.GenreVO

class GenreAdapter(context : Context) : BaseRecyclerAdapter<GenreViewHolder,GenreVO>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view : View = layoutInflater.inflate(R.layout.genre_item,parent,false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder!!.bind(data!!.get(position))
    }
}