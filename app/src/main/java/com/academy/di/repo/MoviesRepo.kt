package com.academy.di.repo

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.db.MovieDao
import com.academy.db.model.Movie
import com.academy.db.model.MovieModelConverter
import com.academy.di.di.Injector
import com.academy.network.services.TmdbApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MoviesRepo @Inject constructor(
    private val movieDao: MovieDao,
    private val tmdbApiService: TmdbApiService,
    private val dataStore: DataStore<Preferences>
) : CoroutineScope {

    init {
        Log.w("Academy", "MoviesRepo init")
        Injector.appComponent.inject(this)
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO
    private var tempMinNumOfVotes = 0

    private fun getTempMinValue() = dataStore.data.map { preferences ->
        preferences[SettingsRepo.KEY_MIN_VOTES] ?: 2
    }

    // Returns Flow with list of movies from database
    fun getMovies(): Flow<List<Movie>> = movieDao.getMovies().map {
        onMoviesFlowCollection(it)
        it
    }

    private suspend fun onMoviesFlowCollection(it: List<Movie>) {
        val minNumOfVotes = getTempMinValue().first()
        if (it.isEmpty() || minNumOfVotes != tempMinNumOfVotes) {
            tempMinNumOfVotes = minNumOfVotes
            fetchFreshMovies()
        }
    }

    fun fetchFreshMovies() {
        getFreshMoviesAndSaveThemToDBAsync()
    }

    private fun getFreshMoviesAndSaveThemToDBAsync() {
        launch {
            // Fetch fresh list from TMDB API
            val movies = tmdbApiService.getMovies(minNumOfVotes = tempMinNumOfVotes)
            // Convert from network to database model
            val convertedList: List<Movie> = MovieModelConverter.convert(movies)
            // Save converted list to the database
            movieDao.insertAll(convertedList)
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "MoviesRepo onCleared")
    }
}