package com.academy.di.di.components

import com.academy.db.di.DbModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.repo.SettingsRepo
import com.academy.di.ui.details.DetailsFragment
import com.academy.di.ui.home.HomeFragment
import com.academy.network.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

// TODO Step 3 - Add DataStoreModule::class to the list of modules
@Component(modules = [DbModule::class, NetworkModule::class, MoviesModule::class])
@Singleton
interface AppComponent {
    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(settingsRepo: SettingsRepo)
    // TODO Step 4 - Add inject function SettingsFragment
}