package com.nwt.first_kotlin_test.Utils

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtils private constructor(){

    val retrofit : Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    companion object{
        private var getInstance : RetrofitUtils? = null

        val instance : RetrofitUtils
            get() {
                if(getInstance == null){
                    getInstance =
                        RetrofitUtils()
                }

                return getInstance!!
            }
    }

}