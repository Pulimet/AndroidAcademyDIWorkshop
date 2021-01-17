package com.academy.di.di.modules

import com.academy.di.repo.MoviesRepo
import com.academy.di.utils.OkHttpLogs
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class MoviesModule {

    // TODO Step 3 - Remove explicit provider.
    @Provides
    @Singleton
    fun getMoviesRepo() = MoviesRepo()

    @Provides
    @Singleton
    fun getOkHttpLogger(): HttpLoggingInterceptor.Logger = OkHttpLogs()
}
