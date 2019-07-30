package com.nwt.first_kotlin_test.Delegates

import com.nwt.first_kotlin_test.vos.CastVO

interface ClickCastDetail {
    fun onTapCast(castVO : CastVO?)
}