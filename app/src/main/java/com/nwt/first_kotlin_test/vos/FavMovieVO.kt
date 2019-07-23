package com.nwt.first_kotlin_test.vos

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_movies")
 class FavMovieVO(
    @PrimaryKey(autoGenerate = true) var id : Int
    //@Embedded var movieVO: MovieVO
)