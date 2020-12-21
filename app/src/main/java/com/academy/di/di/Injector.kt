package com.academy.di.di

import android.content.Context
import com.academy.db.di.DbModule
import com.academy.di.di.components.AppComponent
import com.academy.di.di.components.DaggerAppComponent
import com.academy.di.di.modules.DataStoreModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.SettingsModule
import com.academy.network.di.NetworkModule

object Injector {
    lateinit var appComponent: AppComponent

    fun buildDaggerAppComponent(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            .dataStoreModule(DataStoreModule(appContext))
            .settingsModule(SettingsModule())
            .build()
    }
}