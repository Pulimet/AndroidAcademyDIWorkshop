package com.academy.di.di

import android.content.Context
import com.academy.db.di.DbModule
import com.academy.di.di.components.AppComponent
import com.academy.di.di.components.DaggerAppComponent
import com.academy.di.di.modules.MoviesModule
import com.academy.network.di.NetworkModule

object Injector {
    lateinit var appComponent: AppComponent

    // TODO Step 2 - Create DataStoreModule provides to instances of DataStore by using @Named annotation
    // TODO Step 2 - Add dataStoreModule and pass a context to its constructor
    fun buildDaggerAppComponent(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            .build()
    }
}