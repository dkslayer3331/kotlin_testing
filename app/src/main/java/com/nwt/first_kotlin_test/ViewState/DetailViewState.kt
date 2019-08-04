package com.nwt.first_kotlin_test.ViewState

import com.nwt.first_kotlin_test.vos.MovieVO

sealed class DetailViewState {

    object MovieDetailViewStateLoading : DetailViewState()

    data class MovieDetailViewStateSuccess(val movieVO: MovieVO) : DetailViewState()

    data class MovieDetailViewStateFail(val message : String) : DetailViewState()

}