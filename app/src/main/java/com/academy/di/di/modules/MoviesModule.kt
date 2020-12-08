package com.academy.di.di.modules

import com.academy.di.repo.MoviesRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides
    @Singleton
    fun getMoviesRepo() = MoviesRepo()
}