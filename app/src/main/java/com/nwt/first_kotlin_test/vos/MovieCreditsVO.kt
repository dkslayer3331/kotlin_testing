package com.nwt.first_kotlin_test.vos

import com.google.gson.annotations.SerializedName

//for known movies of a cast

data class MovieCreditsVO(@SerializedName("cast") val known_movies : List<MovieVO> = listOf())