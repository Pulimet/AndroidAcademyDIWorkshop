package com.academy.di.di.modules

import com.academy.di.repo.MoviesRepo
import com.academy.di.utils.OkHttpLogs
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class MoviesModule {

    // TODO Step 1 - Add parameters movieDao, movieFavoriteDao, tmdbApiService to getMoviesRepo
    //  function and pass them to MoviesRepo constructor
    @Provides
    @Singleton
    fun getMoviesRepo() = MoviesRepo()

    @Provides
    @Singleton
    fun getOkHttpLogger(): HttpLoggingInterceptor.Logger = OkHttpLogs()
}
