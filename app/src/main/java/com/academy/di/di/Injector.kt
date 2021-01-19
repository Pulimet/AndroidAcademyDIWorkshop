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
import com.academy.network.di.NetworkModule
import com.academy.ui_favorites.di.DiHolder
import com.academy.ui_favorites.di.FavoritesComponent
import com.academy.ui_favorites.di.FavoritesInjector

object Injector : FavoritesInjector {
    lateinit var appComponent: AppComponent
    private var settingsComponent: SettingsComponent? = null
    // TODO Step 1 - Add variable for FavoritesComponent

    // TODO: Nothing TODO, just note how we are saving a reference for our injector in shared 'navigation' module
    init {
        DiHolder.favoritesInjector = this
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
    // TODO Step 2 - Add getFavoritesComponent and clearFavoritesComponent functions and their implementation
    override fun getFavoritesComponent(): FavoritesComponent {
    }

    override fun clearFavoritesComponent() {
    }
}