package com.academy.di.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.db.dao.MovieDao
import com.academy.db.dao.MovieFavoriteDao
import com.academy.di.example.ImLogOnCreation
import com.academy.di.example.LogOnCreationDemo
import com.academy.di.repo.MoviesRepo
import com.academy.di.utils.OkHttpLogs
import com.academy.network.services.TmdbApiService
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

@Module
class MoviesModule {

    @Provides
    @Singleton
    fun getMoviesRepo(
        movieDao: MovieDao,
        movieFavoriteDao: MovieFavoriteDao,
        tmdbApiService: TmdbApiService,
        @Named("Votes") dataStoreVotes: DataStore<Preferences>,
        @Named("Rating") dataStoreRating: DataStore<Preferences>,
        logOnCreationDemo: LogOnCreationDemo
    ) = MoviesRepo(movieDao, movieFavoriteDao, tmdbApiService, dataStoreVotes, dataStoreRating, logOnCreationDemo)


    @Provides
    @Singleton
    fun getLogOnCreationDemo(
        @Named("LogSingleton") imLogOnCreationSingleton: Lazy<ImLogOnCreation>,
        @Named("LogNotSingleton") imLogOnCreationNotSingleton: Lazy<ImLogOnCreation>,
    ) = LogOnCreationDemo(
        imLogOnCreationSingleton,
        imLogOnCreationNotSingleton,
    )

    @Provides
    @Singleton
    fun getOkHttpLogger(): HttpLoggingInterceptor.Logger = OkHttpLogs()
}
