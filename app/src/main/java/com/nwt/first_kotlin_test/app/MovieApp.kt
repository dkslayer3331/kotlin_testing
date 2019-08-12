package com.nwt.first_kotlin_test.app

import android.app.Application
import com.nwt.first_kotlin_test.BuildConfig
import com.nwt.first_kotlin_test.di.appModule
import com.nwt.first_kotlin_test.di.dataModule
import com.nwt.first_kotlin_test.di.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@MovieApp)
            modules(listOf(appModule,dataModule,viewmodelModule))
        }
    }


}