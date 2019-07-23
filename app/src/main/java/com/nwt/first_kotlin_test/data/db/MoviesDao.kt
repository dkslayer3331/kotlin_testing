package com.nwt.first_kotlin_test.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nwt.first_kotlin_test.vos.FavMovieVO
import io.reactivex.Observable

@Dao
interface MoviesDao {

    @Query("select * from fav_movies")
    fun getAllFavMovies() : Observable<List<FavMovieVO>>

    @Query("delete from fav_movies")
    fun clearAllFavMovies()

    @Insert
    fun addFavMovie(favMovieVO: FavMovieVO)

    @Query("delete from fav_movies where id = :movieid")
    fun removeFavMovieById(movieid : Long)
}