package com.academy.di.di

import android.content.Context
import com.academy.di.di.components.AppComponent
import com.academy.di.di.components.DaggerAppComponent
import com.academy.di.di.components.SettingsComponent
import com.academy.di.di.modules.DbModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.NetworkModule
import com.academy.di.di.modules.SettingsModule

object Injector {
    lateinit var appComponent: AppComponent

    private var settingsComponent: SettingsComponent? = null

    fun buildDaggerAppComponent(applicationContext: Context) {
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(applicationContext))
            .networkModule(NetworkModule())
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