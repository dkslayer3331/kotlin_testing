package com.nwt.first_kotlin_test.data.repository.remote

import androidx.lifecycle.ReportFragment
import com.nwt.first_kotlin_test.vos.CastVO
import com.nwt.first_kotlin_test.vos.MovieListVO
import com.nwt.first_kotlin_test.vos.MovieVO
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteDataSource constructor(private val movieAPI : MoviesAPI ) {

    fun getPopularMovies() : Observable<MovieListVO>{
        return movieAPI.getPopularMovies("a7fc563ba6989aec1e19d62d2d1985c9")
    }

    fun getTopRatedMovies() : Observable<MovieListVO>{
        return movieAPI.getTopRatedMovies("a7fc563ba6989aec1e19d62d2d1985c9")
    }

    fun getUpcomingMovies() : Observable<MovieListVO>{
        return movieAPI.getUpcomingMovies("a7fc563ba6989aec1e19d62d2d1985c9")
    }

    fun getCastDetail(id : Long,movie_credits : String ="movie_credits") : Observable<CastVO>{
        return movieAPI.getCastDetail(id,"a7fc563ba6989aec1e19d62d2d1985c9",movie_credits)
    }

    fun getMovieDetail(id : Long,credits : String = "credits") : Observable<MovieVO>{
        return movieAPI.getMovieDetail(id,"a7fc563ba6989aec1e19d62d2d1985c9",credits)
    }




}