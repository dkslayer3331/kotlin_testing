package com.nwt.first_kotlin_test

import com.nwt.first_kotlin_test.vos.MovieVO

sealed class UpcomingViewState {
    object Loading : UpcomingViewState()
    data class Success(val movieList : List<MovieVO>) : UpcomingViewState()
    data class Error(val message : String) : UpcomingViewState()
    data class InternetConnection(val connection : String) : UpcomingViewState()
}