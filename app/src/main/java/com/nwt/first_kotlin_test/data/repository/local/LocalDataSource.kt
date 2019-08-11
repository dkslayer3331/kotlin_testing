package com.nwt.first_kotlin_test.data.repository.local

import com.nwt.first_kotlin_test.data.db.AppDatabase
import com.nwt.first_kotlin_test.data.db.MoviesDao
import com.nwt.first_kotlin_test.vos.FavMovieVO
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class LocalDataSource constructor(private val db : AppDatabase) {

    //koin inject

    fun getAllFavMovies() : Observable<List<FavMovieVO>>?{
        return  db.favDao().getAllFavMovies()
    }

    fun clearAllFavMovies(){
        db?.favDao()?.clearAllFavMovies()
    }

    fun addFavMovie(movieVO: FavMovieVO){
        db?.favDao()?.addFavMovie(movieVO)
    }

    fun removeFavMovieById(id : Long){
        db?.favDao()?.removeFavMovieById(id)
    }

}