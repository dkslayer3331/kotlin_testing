package com.nwt.first_kotlin_test.vos

import com.google.gson.annotations.SerializedName

data class CastVO(
    @SerializedName("cast_id") val castId : Long = 0,
    @SerializedName("name") val name : String = "",
    @SerializedName("id") val id : Long = 0,
    @SerializedName("profile_path") val profilePath : String = "",
    @SerializedName("character") val character : String = "",
    @SerializedName("birthday") val birthday : String = "",
    @SerializedName("known_for_department") val known_department : String = "",
    @SerializedName("deathday") val deathday : String = "",
    @SerializedName("biography") val bio : String = "",
    @SerializedName("movie_credits") val movie_credits : MovieCreditsVO = MovieCreditsVO()
)