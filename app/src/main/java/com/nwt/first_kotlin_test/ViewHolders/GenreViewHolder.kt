package com.nwt.first_kotlin_test.ViewHolders

import android.view.View
import com.nwt.first_kotlin_test.vos.GenreVO
import kotlinx.android.synthetic.main.genre_item.view.*

class GenreViewHolder(itemView: View) : BaseViewHolder<GenreVO>(itemView) {

    var genreVO : GenreVO? = null

    override fun bind(data: GenreVO) {
        //binding with views
        genreVO = data
        itemView.genre_name.text = genreVO!!.name
    }
}