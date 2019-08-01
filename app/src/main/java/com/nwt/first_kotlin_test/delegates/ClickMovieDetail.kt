package com.nwt.first_kotlin_test.delegates

import com.nwt.first_kotlin_test.vos.MovieVO

interface ClickMovieDetail {
    fun onTap(movieVO: MovieVO?)
}