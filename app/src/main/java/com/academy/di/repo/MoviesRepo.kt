package com.academy.di.repo

import android.util.Log
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite
import com.academy.db.utils.MovieModelConverter
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
    private var tempMinRating = 0

    private fun getTempMinValue() = Dependencies.dataStoreMinVotes.data.map { preferences ->
        preferences[SettingsRepo.KEY_MIN_VOTES] ?: 2
    }

    private fun getTempMinRating() = Dependencies.dataStoreMinRating.data.map { preferences ->
        preferences[SettingsRepo.KEY_MIN_RATING] ?: 2
    }

    // Returns Flow with list of movies from database
    fun getMovies(): Flow<List<Movie>> = Dependencies.getMovieDao().getMovies().map {
        ifFirstTimeOrSettingsChangedFetchFreshMovies(it)
        it
    }

    private suspend fun ifFirstTimeOrSettingsChangedFetchFreshMovies(it: List<Movie>) {
        Log.d("Academy", "onMoviesFlowCollection, size: ${it.size}")
        val minNumOfVotes = getTempMinValue().first()
        val minRating = getTempMinRating().first()
        if (minNumOfVotes != tempMinNumOfVotes || minRating != tempMinRating) {
            tempMinNumOfVotes = minNumOfVotes
            tempMinRating = minRating
            fetchFreshMovies()
        }
    }

    fun fetchFreshMovies() {
        Log.d("Academy", "fetchFreshMovies")
        getFreshMoviesAndSaveThemToDBAsync()
    }

    private fun getFreshMoviesAndSaveThemToDBAsync() {
        launch {
            // Fetch fresh list from TMDB API
            val movies = Dependencies.getApiServices().getMovies(
                minNumOfVotes = tempMinNumOfVotes,
                minRating = tempMinRating
            )
            // Convert from network to database model
            val convertedList: List<Movie> =
                MovieModelConverter.convertTmdbResultsToListOfMovies(movies)
            // Save converted list to the database
            Dependencies.getMovieDao().deleteAll()
            Dependencies.getMovieDao().insertAll(convertedList)
        }
    }

    // Favorites
    fun getMovieFromFavorites(movieId: Int): Flow<MovieFavorite> =
        Dependencies.getMovieFavoriteDao().getMovie(movieId)

    suspend fun addOrRemoveMovieFromFavorites(movie: Movie, movieInFavorites: Boolean) {
        val movieFavorite: MovieFavorite = MovieModelConverter.convertMovieToMovieFavorite(movie)
        Dependencies.getMovieFavoriteDao().run {
            if (movieInFavorites) delete(movieFavorite) else insert(movieFavorite)
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "MoviesRepo onCleared")
    }
}