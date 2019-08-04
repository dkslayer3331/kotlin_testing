package com.nwt.first_kotlin_test.data.repository

import com.nwt.first_kotlin_test.data.repository.local.LocalDataSource
import com.nwt.first_kotlin_test.data.repository.remote.RemoteDataSource
import com.nwt.first_kotlin_test.data.db.MoviesDao
import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieListVO
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MoviesRepository private constructor(localDataSource: LocalDataSource,remoteDataSource: RemoteDataSource){

    var localDataSource : LocalDataSource = localDataSource
    var remoteDataSource : RemoteDataSource = remoteDataSource

    companion object{
        var objInstance : MoviesRepository? = null

        fun getInstance(dao: MoviesDao):MoviesRepository{
            if(objInstance == null){
                objInstance = MoviesRepository(LocalDataSource.getInstance(dao), RemoteDataSource.instance)
            }

                return objInstance!!
        }

    }

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