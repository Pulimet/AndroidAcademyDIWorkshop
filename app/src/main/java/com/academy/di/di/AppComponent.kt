package com.academy.di.di

import com.academy.di.di.modules.DbModule
import com.academy.di.di.modules.MoviesModule
import com.academy.di.di.modules.NetworkModule
import com.academy.di.repo.MoviesRepo
import com.academy.di.ui.home.HomeFragment
import com.academy.di.ui.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class, NetworkModule::class, MoviesModule::class])
@Singleton
interface AppComponent {
    fun inject(moviesRepo: MoviesRepo)
    fun inject(homeFragment: HomeFragment)
}