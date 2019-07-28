package com.nwt.first_kotlin_test.app

import android.app.Application
import com.nwt.first_kotlin_test.BuildConfig
import timber.log.Timber

class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

}