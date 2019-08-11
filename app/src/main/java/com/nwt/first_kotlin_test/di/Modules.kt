package com.nwt.first_kotlin_test.di

import com.nwt.first_kotlin_test.data.repository.remote.NetworkInject
import com.nwt.first_kotlin_test.data.db.DatabaseInjector
import com.nwt.first_kotlin_test.data.repository.MoviesRepository
import com.nwt.first_kotlin_test.data.repository.local.LocalDataSource
import com.nwt.first_kotlin_test.data.repository.remote.RemoteDataSource
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module(override = true) {

    single { NetworkInject().provideAPI(getProperty("baseUrl")) }

    single { LocalDataSource(get()) }

    single { RemoteDataSource(get()) }

    single { DatabaseInjector().provideAppDatabase(androidContext()) }

    single { MoviesRepository(get(),get())}

    viewModel { AppViewModel(get()) }


}