package com.academy.di.di

import com.academy.di.repo.MoviesRepo
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class])
@Singleton
interface AppComponent {
    fun inject(moviesRepo: MoviesRepo)
}