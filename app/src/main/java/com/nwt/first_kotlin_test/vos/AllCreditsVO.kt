package com.nwt.first_kotlin_test.vos

import com.google.gson.annotations.SerializedName

data class AllCreditsVO(
    @SerializedName("cast") val cast : List<CastVO> = listOf(),
    @SerializedName("crew") val crew : List<CrewVO> = listOf()
)