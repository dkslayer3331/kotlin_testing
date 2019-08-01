package com.nwt.first_kotlin_test.delegates

import com.nwt.first_kotlin_test.vos.CastVO

interface ClickCastDetail {
    fun onTapCast(castVO : CastVO?)
}