package com.academy.di.di.modules

import com.academy.db.dao.MovieDao
import com.academy.db.dao.MovieFavoriteDao
import com.academy.di.repo.MoviesRepo
import com.academy.di.utils.OkHttpLogs
import com.academy.network.services.TmdbApiService
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class MoviesModule {

    @Provides
    @Singleton
    fun getMoviesRepo(
        movieDao: MovieDao,
        movieFavoriteDao: MovieFavoriteDao,
        tmdbApiService: TmdbApiService
    ) = MoviesRepo(movieDao, movieFavoriteDao, tmdbApiService)

    @Provides
    @Singleton
    fun getOkHttpLogger(): HttpLoggingInterceptor.Logger = OkHttpLogs()
}
