package com.nwt.first_kotlin_test.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.nwt.first_kotlin_test.delegates.ClickCastDetail
import com.nwt.first_kotlin_test.R
import com.nwt.first_kotlin_test.viewholders.CastViewHolder
import com.nwt.first_kotlin_test.vos.CastVO

class CastAdapter(context: Context,clickCastDetail: ClickCastDetail) : BaseRecyclerAdapter<CastViewHolder,CastVO>(context) {

    val clickCastDetail : ClickCastDetail = clickCastDetail

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view : View = layoutInflater.inflate(R.layout.cast_item,parent,false)
        return CastViewHolder(view, clickCastDetail)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
       holder!!.bind(data!!.get(position))
    }
}