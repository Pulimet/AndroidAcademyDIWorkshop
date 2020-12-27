package com.academy.di.di

import android.content.Context
import com.academy.db.di.DbModule
import com.academy.di.di.components.AppComponent
import com.academy.di.di.components.DaggerAppComponent
import com.academy.di.di.components.SettingsComponent
import com.academy.di.di.modules.DataStoreModule
import com.academy.di.di.modules.ExamplesModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.SettingsModule
import com.academy.navigation.BaseFavoritesComponent
import com.academy.navigation.BaseInjector
import com.academy.navigation.DiHolder
import com.academy.network.di.NetworkModule

object Injector : BaseInjector {
    lateinit var appComponent: AppComponent
    private var settingsComponent: SettingsComponent? = null
    // TODO Step 5 - Add variable for FavoritesComponent

    // TODO Step 5 - Nothing TODO, just note how we are saving a reference for our injector in shared 'navigation' module
    init {
        DiHolder.baseInjector = this
    }

    fun buildDaggerAppComponent(appContext: Context) {
        appComponent = DaggerAppComponent.builder()
            .moviesModule(MoviesModule())
            .dbModule(DbModule(appContext))
            .networkModule(NetworkModule())
            .dataStoreModule(DataStoreModule(appContext))
            .examplesModule(ExamplesModule())
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

    // Favorites
    // TODO Step 5 - Add getFavoritesComponent and clearFavoritesComponent functions
    // TODO Step 5 - Nothing TODO, just note that we use BaseFavoritesComponent interface from 'navigation' module
    override fun getFavoritesComponent(): BaseFavoritesComponent {
        TODO("Not yet implemented")
    }

    override fun clearFavoritesComponent() {
        TODO("Not yet implemented")
    }
}