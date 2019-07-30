package com.nwt.first_kotlin_test.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nwt.first_kotlin_test.delegates.ClickCastDetail
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.vos.CastVO
import kotlinx.android.synthetic.main.cast_item.view.*

class CastViewHolder(itemView: View,castDetail: ClickCastDetail) : BaseViewHolder<CastVO>(itemView),View.OnClickListener {

    var castVO : CastVO? = null

    var clickCastDetail : ClickCastDetail = castDetail

    override fun bind(data: CastVO) {
        this.castVO = data

        itemView.cast_character_name.text = data.character

        itemView.original_cast_name.text = data.name

        Glide.with(itemView.context)
            .load("http://image.tmdb.org/t/p/w300"+data.profilePath)
            .placeholder(R.drawable.digimon)
            .into(itemView.cast_poster)
    }

    override fun onClick(v: View?) {
        super.onClick(v)
        clickCastDetail.onTapCast(castVO)
    }
}