package com.academy.di.di

import android.content.Context
import com.academy.di.di.components.AppComponent
import com.academy.di.di.components.DaggerAppComponent
import com.academy.di.di.components.SettingsComponent
import com.academy.di.di.modules.*

object Injector {
    lateinit var appComponent: AppComponent

    private var settingsComponent: SettingsComponent? = null
    private lateinit var appContext: Context

    fun buildDaggerAppComponent(applicationContext: Context) {
        appContext = applicationContext
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            .dataStoreModule(DataStoreModule(appContext))
            .build()
    }

    fun getSettingsComponent(): SettingsComponent {
        if (settingsComponent == null) {
            settingsComponent = appComponent.addSettingsSubComponent(SettingsModule())
        }
        return settingsComponent as SettingsComponent
    }

    fun clearSettingsComponent() {
        settingsComponent = null
    }
}