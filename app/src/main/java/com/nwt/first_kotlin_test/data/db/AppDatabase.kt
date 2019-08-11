package com.nwt.first_kotlin_test.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nwt.first_kotlin_test.vos.FavMovieVO

@Database(entities = [FavMovieVO::class],exportSchema = false,version = 1)
abstract class AppDatabase : RoomDatabase() {

    //koin inject

    abstract fun favDao() : MoviesDao

//    companion object{
//        private var instance : AppDatabase? = null
//
//        fun getInMemoryDatabase(context : Context) : AppDatabase{
//            if(instance == null){
//                    instance = Room.inMemoryDatabaseBuilder<AppDatabase>(context.applicationContext,AppDatabase::class.java)
//                        .fallbackToDestructiveMigration()
//                        .build()
//            }
//            return instance as AppDatabase
//        }
//
//        fun clearInstance(){
//            instance = null
//        }
//
//    }

}