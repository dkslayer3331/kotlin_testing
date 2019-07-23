package com.nwt.first_kotlin_test.vos

import com.google.gson.annotations.SerializedName

data class MovieListVO(
    @SerializedName("page") val page:Int = 0,
    @SerializedName("results") val results:List<MovieVO> = listOf(),
    @SerializedName("total_results") val totalResults:Int = 0,
    @SerializedName("total_pages") val totalPages:Int = 0
)