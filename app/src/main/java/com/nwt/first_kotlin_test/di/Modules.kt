package com.nwt.first_kotlin_test.di

import com.nwt.first_kotlin_test.data.Repository.remote.NetworkInject
import com.nwt.first_kotlin_test.data.db.DatabaseInjector
import com.nwt.first_kotlin_test.data.repository.MoviesRepository
import com.nwt.first_kotlin_test.data.repository.local.LocalDataSource
import com.nwt.first_kotlin_test.data.repository.remote.MoviesAPI
import com.nwt.first_kotlin_test.data.repository.remote.RemoteDataSource
import com.nwt.first_kotlin_test.data.viewmodels.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module(override = true) {

    single<MoviesAPI> { NetworkInject().provideAPI(getProperty<String>("baseUrl")) }

    single { RemoteDataSource() }

    single { DatabaseInjector().provideAppDatabase(get()) }

    single { LocalDataSource() }

    single { MoviesRepository() }

    viewModel { AppViewModel(get()) }

}