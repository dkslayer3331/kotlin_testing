package com.nwt.first_kotlin_test.viewState

import com.nwt.first_kotlin_test.vos.CastVO

sealed class CastDetailViewState {

    object CastDetailViewStateLoading : CastDetailViewState()

    data class CastDetailViewStateSuccess(val castVO: CastVO) : CastDetailViewState()

    data class CastDetailViewStateFail(val message : String) : CastDetailViewState()

}
