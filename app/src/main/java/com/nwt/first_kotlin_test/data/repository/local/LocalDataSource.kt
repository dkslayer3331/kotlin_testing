package com.nwt.first_kotlin_test.data.repository.local

import com.nwt.first_kotlin_test.data.db.MoviesDao
import com.nwt.first_kotlin_test.vos.FavMovieVO
import io.reactivex.Observable

class LocalDataSource private constructor(favDao: MoviesDao){

    var favDao : MoviesDao? = null

    init {
        this.favDao = favDao
    }

    companion object{
        var objInstance : LocalDataSource? = null

        fun getInstance(favDao: MoviesDao) : LocalDataSource {
            if (objInstance == null){
                objInstance = LocalDataSource(favDao)
            }

            return objInstance!!
        }

    }

    fun getAllFavMovies() : Observable<List<FavMovieVO>>?{
        return favDao?.getAllFavMovies()
    }

    fun clearAllFavMovies(){
        favDao?.clearAllFavMovies()
    }

    fun addFavMovie(movieVO: FavMovieVO){
        favDao?.addFavMovie(movieVO)
    }

    fun removeFavMovieById(id : Long){
        favDao?.removeFavMovieById(id)
    }

}