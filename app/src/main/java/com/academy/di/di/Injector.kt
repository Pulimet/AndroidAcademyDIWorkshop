package com.academy.di.di

import android.content.Context
import com.academy.db.di.DbModule
import com.academy.di.di.components.AppComponent
import com.academy.di.di.components.DaggerAppComponent
import com.academy.di.di.components.SettingsComponent
import com.academy.di.di.modules.DataStoreModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.SettingsModule
import com.academy.network.di.NetworkModule

object Injector {
    lateinit var appComponent: AppComponent
    private var settingsComponent: SettingsComponent? = null

    fun buildDaggerAppComponent(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            .dataStoreModule(DataStoreModule(appContext))
             // TODO Step 5 - Add ExampleModule
            .build()
    }

    // Settings
    fun getSettingsComponent(): SettingsComponent {
        if (settingsComponent == null) {
            settingsComponent = appComponent
                .addSettingsSubComponent()
                .settingsModule(SettingsModule())
                .build()
        }
        return settingsComponent as SettingsComponent
    }

    fun clearSettingsComponent() {
        settingsComponent = null
    }
}