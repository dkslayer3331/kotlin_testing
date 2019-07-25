package com.nwt.first_kotlin_test.Delegates

import com.nwt.first_kotlin_test.vos.MovieVO

interface ClickMovieDetail {
    fun onTap(movieVO: MovieVO?)
}