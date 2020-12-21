package com.academy.di.di.components

import com.academy.db.di.DbModule
import com.academy.di.di.modules.DataStoreModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.SettingsModule
import com.academy.di.repo.MoviesRepo
import com.academy.di.repo.SettingsRepo
import com.academy.di.ui.details.DetailsFragment
import com.academy.di.ui.home.HomeFragment
import com.academy.di.ui.settings.SettingsFragment
import com.academy.network.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

// TODO Step 3 - Remove SettingsModule
@Component(modules = [DbModule::class, NetworkModule::class, MoviesModule::class,  DataStoreModule::class, SettingsModule::class])
@Singleton
interface AppComponent {
    // TODO Step 3 - Add function adding Settings SubComponent and returns SettingsComponent.Builder

    fun inject(moviesRepo: MoviesRepo)
    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(settingsRepo: SettingsRepo)

    // TODO Step 3 - Remove SettingsFragment inject
    fun inject(settingsFragment: SettingsFragment)
}