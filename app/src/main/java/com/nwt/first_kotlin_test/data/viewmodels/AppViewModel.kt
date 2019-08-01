package com.nwt.first_kotlin_test.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nwt.first_kotlin_test.DetailViewState
import com.nwt.first_kotlin_test.MainViewState
import com.nwt.first_kotlin_test.UpcomingViewState
import com.nwt.first_kotlin_test.data.repository.MoviesRepository
import com.nwt.first_kotlin_test.data.db.AppDatabase
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.schedulers.Schedulers

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private var moviesRepository : MoviesRepository

    private var appDatabase : AppDatabase

    var viewState : MutableLiveData<MainViewState> = MutableLiveData()

    var detailViewState : MutableLiveData<DetailViewState> = MutableLiveData()

    var upcomingViewState : MutableLiveData<UpcomingViewState> = MutableLiveData()

    init {
        appDatabase = AppDatabase.getInMemoryDatabase(application.applicationContext)
        moviesRepository = MoviesRepository.getInstance(appDatabase.favDao())
        mainScreenData()
    }

    private fun mainScreenData(){
        var list : List<MovieVO> = emptyList()
        moviesRepository.getPopularMovies()
            .flatMap {
                list = it.results
                moviesRepository.getUpcomingMovies()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { viewState.postValue(MainViewState.PopularMoviesLoadingState)  }
            .doOnError { viewState.postValue(MainViewState.PopularMovieFailState(it.localizedMessage)) }
            .subscribe { viewState.postValue(MainViewState.PopularMovieSuccessState(list, it.results)) }
    }

    fun getMovieDetail(id : Long) {
        moviesRepository.getMovieDetail(id)
            .doOnSubscribe { t ->detailViewState.postValue(DetailViewState.MovieDetailViewStateLoading) }
            .doOnError { t -> detailViewState.postValue(DetailViewState.MovieDetailViewStateFail(t.localizedMessage)) }
            .subscribe{ t -> detailViewState.postValue(DetailViewState.MovieDetailViewStateSuccess(t))  }
    }

    override fun onCleared() {
        super.onCleared()
        viewState.value = null
        detailViewState.value = null
        upcomingViewState.value = null
    }

}