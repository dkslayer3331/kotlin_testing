package com.nwt.first_kotlin_test.ViewState

import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieVO

sealed class CastDetailViewState {

    object CastDetailViewStateLoading : CastDetailViewState()

    data class CastDetailViewStateSuccess(val castVO: CastVO) : CastDetailViewState()

    data class CastDetailViewStateFail(val message : String) : CastDetailViewState()

}
