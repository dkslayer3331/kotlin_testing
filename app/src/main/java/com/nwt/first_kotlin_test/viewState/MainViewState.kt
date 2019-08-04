package com.nwt.first_kotlin_test.viewState

import com.nwt.first_kotlin_test.vos.MovieVO

sealed class MainViewState {

    object PopularMoviesLoadingState : MainViewState()

    data class PopularMovieSuccessState(val list : List<MovieVO>, val upcoing : List<MovieVO>) : MainViewState()

    data class PopularMovieFailState(val message : String) : MainViewState()

}