package com.nwt.first_kotlin_test

import com.nwt.first_kotlin_test.vos.MovieVO

sealed class MainViewState {

    object PopularMoviesLoadingState : MainViewState()

    data class PopularMovieSuccessState(val list : List<MovieVO>) : MainViewState()

    data class PopularMovieFailState(val message : String) : MainViewState()

}