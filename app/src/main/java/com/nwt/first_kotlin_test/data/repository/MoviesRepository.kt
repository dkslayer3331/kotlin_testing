package com.nwt.first_kotlin_test.data.repository

import com.nwt.first_kotlin_test.data.repository.local.LocalDataSource
import com.nwt.first_kotlin_test.data.repository.remote.RemoteDataSource
import com.nwt.first_kotlin_test.data.db.MoviesDao
import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieListVO
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesRepository constructor(
    private val localDataSource : LocalDataSource,
    private val remoteDataSource : RemoteDataSource){
    //koin inject

    fun getPopularMovies() : Observable<MovieListVO>{
        return remoteDataSource.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun getUpcomingMovies() : Observable<MovieListVO>{
        return remoteDataSource.getUpcomingMovies()
    }

    fun getMovieDetail(id: Long) : Observable<MovieVO>{
        return remoteDataSource.getMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }

    fun getCastDetail(id : Long) : Observable<CastVO>{
        return remoteDataSource.getCastDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }



}