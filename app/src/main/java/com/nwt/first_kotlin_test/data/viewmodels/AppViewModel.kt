package com.nwt.first_kotlin_test.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nwt.first_kotlin_test.DetailViewState
import com.nwt.first_kotlin_test.MainViewState
import com.nwt.first_kotlin_test.data.Repository.MoviesRepository
import com.nwt.first_kotlin_test.data.db.AppDatabase
import com.nwt.first_kotlin_test.vos.MovieListVO
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private var moviesRepository : MoviesRepository

    private var appDatabase : AppDatabase

    var viewState : MutableLiveData<MainViewState> = MutableLiveData()

    var detailViewState : MutableLiveData<DetailViewState> = MutableLiveData()

    init {
        appDatabase = AppDatabase.getInMemoryDatabase(application.applicationContext)
        moviesRepository = MoviesRepository.getInstance(appDatabase.favDao())
    }

    fun popular(){
        moviesRepository.getPopularMovies()
            .doOnSubscribe { t -> viewState.postValue(MainViewState.PopularMoviesLoadingState)  }
            .doOnError { t -> viewState.postValue(MainViewState.PopularMovieFailState(t.localizedMessage)) }
            .subscribe { t -> viewState.postValue(MainViewState.PopularMovieSuccessState(t.results)) }
    }


//    fun getUpcoming() : Observable<MovieListVO>{
//        return moviesRepository.getPopularMovies()
//    }

//    fun getMovieDetail(id:Long) : Observable<MovieVO>{
//        return moviesRepository.getMovieDetail(id)
//    }

    fun getMovieDetail(id : Long) {
        moviesRepository.getMovieDetail(id)
            .doOnSubscribe { t ->detailViewState.postValue(DetailViewState.MovieDetailViewStateLoading) }
            .doOnError { t -> detailViewState.postValue(DetailViewState.MovieDetailViewStateFail(t.localizedMessage)) }
            .subscribe{ t -> detailViewState.postValue(DetailViewState.MovieDetailViewStateSuccess(t))  }
    }

}