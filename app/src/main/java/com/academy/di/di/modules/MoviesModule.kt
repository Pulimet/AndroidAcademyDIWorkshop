package com.academy.di.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.db.MovieDao
import com.academy.di.example.ImLogOnCreation
import com.academy.di.example.LogOnCreationDemo
import com.academy.di.repo.MoviesRepo
import com.academy.network.services.TmdbApiService
import dagger.Lazy
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides
    @Singleton
    fun getMoviesRepo(
        movieDao: MovieDao,
        tmdbApiService: TmdbApiService,
        @Named("Votes") dataStoreVotes: DataStore<Preferences>,
        @Named("Rating") dataStoreRating: DataStore<Preferences>,
        logOnCreationDemo: LogOnCreationDemo
    ) = MoviesRepo(movieDao, tmdbApiService, dataStoreVotes, dataStoreRating, logOnCreationDemo)

    @Provides
    @Singleton
    fun getLogOnCreationDemo(
        @Named("LogSingleton") imLogOnCreationSingleton: Lazy<ImLogOnCreation>,
        @Named("LogSingleton") imLogOnCreationSingleton2: Lazy<ImLogOnCreation>,
        @Named("LogNotSingleton") imLogOnCreationNotSingleton: Lazy<ImLogOnCreation>,
        @Named("LogNotSingleton") imLogOnCreationNotSingleton2: Lazy<ImLogOnCreation>,
        @Named("ProviderExample") imLogOnCreationProviderExample: Provider<ImLogOnCreation>,
    ) = LogOnCreationDemo(
        imLogOnCreationSingleton,
        imLogOnCreationSingleton2,
        imLogOnCreationNotSingleton,
        imLogOnCreationNotSingleton2,
        imLogOnCreationProviderExample
    )
}