package com.academy.di.di.components

import com.academy.di.di.modules.*
import com.academy.di.example.LogOnCreationDemo
import com.academy.di.repo.MoviesRepo
import com.academy.di.repo.SettingsRepo
import com.academy.di.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class, NetworkModule::class, MoviesModule::class, DataStoreModule::class, ExamplesModule::class])
@Singleton
interface AppComponent {
    fun addSettingsSubComponent(settings: SettingsModule): SettingsComponent

    fun inject(moviesRepo: MoviesRepo)
    fun inject(homeFragment: HomeFragment)
    fun inject(settingsRepo: SettingsRepo)
    fun inject(logOnCreationDemo: LogOnCreationDemo)
}