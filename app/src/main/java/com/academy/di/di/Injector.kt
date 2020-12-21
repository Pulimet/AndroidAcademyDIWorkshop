package com.academy.di.di

import android.content.Context
import com.academy.db.di.DbModule
import com.academy.di.di.components.AppComponent
import com.academy.di.di.components.DaggerAppComponent
import com.academy.di.di.modules.MoviesModule
import com.academy.network.di.NetworkModule

object Injector {
    lateinit var appComponent: AppComponent

    fun buildDaggerAppComponent(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            // TODO Step 2 - Add dataStoreModule and pass a context to its constructor
            // TODO Step 2 - Add settingsModule
            .build()
    }
}