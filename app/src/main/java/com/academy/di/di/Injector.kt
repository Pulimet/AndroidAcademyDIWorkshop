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

    // TODO Step 3 - Create a nullable variable for storing reference of SettingsComponent

    fun buildDaggerAppComponent(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            .dataStoreModule(DataStoreModule(appContext))
            // TODO Step 3 - Remove settingsModule function call
            .settingsModule(SettingsModule())
            .build()
    }

    // TODO Step 3 - Add a function that when settingsComponent variable is null will build it
    //  by using addSettingsSubComponent function from AppComponent and do not forget to add a settingsModule.
    fun getSettingsComponent()/*: SettingsComponent*/ {

    }

    // TODO Step 3 - Add a function that will clear (make it null) settingsComponent variable
    fun clearSettingsComponent() {

    }
}