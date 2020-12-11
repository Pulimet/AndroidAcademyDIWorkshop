package com.academy.di.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.db.MovieDao
import com.academy.di.repo.MoviesRepo
import com.academy.network.services.TmdbApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MoviesModule {
    @Provides
    @Singleton
    fun getMoviesRepo(
        movieDao: MovieDao,
        tmdbApiService: TmdbApiService,
        dataStore: DataStore<Preferences>
    ) =
        MoviesRepo(movieDao, tmdbApiService, dataStore)
}