package com.academy.di.repo

import android.util.Log
import com.academy.db.MovieDao
import com.academy.db.model.Movie
import com.academy.db.model.MovieModelConverter
import com.academy.di.App
import com.academy.network.services.TmdbApiService
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MoviesRepo : CoroutineScope {
    @Inject
    lateinit var movieDao: MovieDao

    @Inject
    lateinit var tmdbApiService: TmdbApiService

    init {
        Log.w("Academy", "MoviesRepo init")
        App.component.inject(this)
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    // Returns Flow with list of movies from database
    fun getMovies() = movieDao.getMovies()

    fun fetchFreshMovies() {
        getFreshMoviesAndSaveThemToDBAsync()
    }

    private fun getFreshMoviesAndSaveThemToDBAsync() {
        launch {
            // Fetch fresh list from TMDB API
            val movies = tmdbApiService.getMovies()
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