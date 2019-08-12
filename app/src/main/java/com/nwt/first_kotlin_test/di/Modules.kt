package com.nwt.first_kotlin_test.di

import com.nwt.first_kotlin_test.data.repository.remote.NetworkInject
import com.nwt.first_kotlin_test.data.db.DatabaseInjector
import com.nwt.first_kotlin_test.data.repository.MoviesRepository
import com.nwt.first_kotlin_test.data.repository.local.LocalDataSource
import com.nwt.first_kotlin_test.data.repository.remote.MoviesAPI
import com.nwt.first_kotlin_test.data.repository.remote.RemoteDataSource
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module(override = true) {

    single { NetworkInject().provideAPI() }

    single { DatabaseInjector().provideAppDatabase(get()) }

}

val dataModule = module{

    single { LocalDataSource(get()) }

    single { RemoteDataSource(get()) }

    single { MoviesRepository(get(),get())}
}

val viewmodelModule = module {
    viewModel { AppViewModel(get()) }
}