package com.academy.di.repo

import android.util.Log
import com.academy.db.model.Movie
import com.academy.db.model.MovieModelConverter
import com.academy.di.di.Dependencies
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class MoviesRepo : CoroutineScope {
    init {
        Log.w("Academy", "MoviesRepo init")
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO
    private var tempMinNumOfVotes = 0

    private fun getTempMinValue() = Dependencies.dataStore.data.map { preferences ->
        preferences[SettingsRepo.KEY_MIN_VOTES] ?: 2
    }

    // Returns Flow with list of movies from database
    fun getMovies(): Flow<List<Movie>> = Dependencies.getMovieDao().getMovies().map {
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
            val movies = Dependencies.getApiServices().getMovies(minNumOfVotes = tempMinNumOfVotes)
            // Convert from network to database model
            val convertedList: List<Movie> = MovieModelConverter.convert(movies)
            // Save converted list to the database
            Dependencies.getMovieDao().insertAll(convertedList)
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "MoviesRepo onCleared")
    }
}