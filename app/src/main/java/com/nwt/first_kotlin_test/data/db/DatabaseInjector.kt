package com.nwt.first_kotlin_test.data.db

import android.content.Context
import androidx.room.Room

class DatabaseInjector {

    fun provideAppDatabase(context: Context) : AppDatabase {
      return  Room.inMemoryDatabaseBuilder<AppDatabase>(context.applicationContext,AppDatabase::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }
}