package com.nwt.first_kotlin_test.data.repository.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkInject {

   private fun provideRetrofit(baseUrl : String) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    }

    fun provideAPI(baseUrl: String) :  MoviesAPI = provideRetrofit(baseUrl).create(MoviesAPI::class.java)

}