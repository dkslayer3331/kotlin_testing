package com.nwt.first_kotlin_test.data.repository.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkInject {

   private fun provideRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    fun provideAPI() :  MoviesAPI {
        return provideRetrofit().create(MoviesAPI::class.java)
    }

}