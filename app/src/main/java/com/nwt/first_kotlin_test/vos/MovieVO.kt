package com.nwt.first_kotlin_test.vos

import com.google.gson.annotations.SerializedName

data class MovieVO (
    @SerializedName("id") val movieId : Long = 0,
    @SerializedName("adult") val adult : Boolean = false,
    @SerializedName("poster_path") val imgUrl : String = "",
    @SerializedName("genres") val genres : List<GenreVO> = listOf(),
    @SerializedName("runtime") val runtime : Long = 0,
    @SerializedName("title") val title : String = "",
    @SerializedName("overview") val overview : String = "",
    @SerializedName("release_date") val release_date : String = ""
    )