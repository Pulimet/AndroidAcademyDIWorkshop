package com.academy.di.di.components

import com.academy.db.di.DbModule
import com.academy.di.di.modules.DataStoreModule
import com.academy.di.di.modules.ExamplesModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.example.LogOnCreationDemo
import com.academy.di.repo.MoviesRepo
import com.academy.di.repo.SettingsRepo
import com.academy.di.ui.details.DetailsFragment
import com.academy.di.ui.home.HomeFragment
import com.academy.network.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class, NetworkModule::class, MoviesModule::class,  DataStoreModule::class,  ExamplesModule::class])
@Singleton
interface AppComponent {
    fun addSettingsSubComponent(): SettingsComponent.Builder
    // TODO Step 5 - Add a function for adding FavoritesComponent

    fun inject(moviesRepo: MoviesRepo)
    fun inject(homeFragment: HomeFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun inject(settingsRepo: SettingsRepo)
    fun inject(logOnCreationDemo: LogOnCreationDemo)
}