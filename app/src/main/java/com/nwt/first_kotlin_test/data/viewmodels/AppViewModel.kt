package com.nwt.first_kotlin_test.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nwt.first_kotlin_test.data.Repository.MoviesRepository
import com.nwt.first_kotlin_test.data.db.AppDatabase
import com.nwt.first_kotlin_test.vos.MovieListVO
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.Observable

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private var moviesRepository : MoviesRepository
    private var appDatabase : AppDatabase

    init {
        appDatabase = AppDatabase.getInMemoryDatabase(application.applicationContext)
        moviesRepository = MoviesRepository.getInstance(appDatabase.favDao())
    }

    fun getPopular(): Observable<MovieListVO>{
            return moviesRepository.getPopularMovies()
    }

    fun getUpcoming() : Observable<MovieListVO>{
        return moviesRepository.getPopularMovies()
    }

    fun getMovieDetail(id:Long) : Observable<MovieVO>{
        return moviesRepository.getMovieDetail(id)
    }

}