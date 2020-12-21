package com.academy.di.di.components

import com.academy.db.di.DbModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.repo.MoviesRepo
import com.academy.di.ui.details.DetailsFragment
import com.academy.di.ui.home.HomeFragment
import com.academy.network.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

// TODO Step 2 - Add DataStoreModule::class to the list of modules
@Component(modules = [DbModule::class, NetworkModule::class, MoviesModule::class])
@Singleton
interface AppComponent {
    fun inject(moviesRepo: MoviesRepo)
    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: DetailsFragment)
    // TODO Step 2 - Add inject function for SettingsRepo and SettingsFragment
}