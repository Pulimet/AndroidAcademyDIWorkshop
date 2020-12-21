package com.academy.di.repo

import android.util.Log
import com.academy.db.dao.MovieDao
import com.academy.db.dao.MovieFavoriteDao
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite
import com.academy.db.utils.MovieModelConverter
import com.academy.di.di.Dependencies
import com.academy.di.di.Injector
import com.academy.network.services.TmdbApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

// TODO Step 2 - Add @Named injection of 'dataStoreVotes' and 'dataStoreRating' to constructor and use them in the class
class MoviesRepo @Inject constructor(
    private val movieDao: MovieDao,
    private val movieFavoriteDao: MovieFavoriteDao,
    private val tmdbApiService: TmdbApiService
) : CoroutineScope {
    init {
        Log.w("Academy", "MoviesRepo init")
        Injector.appComponent.inject(this)
    }

    private val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e("Academy", "Coroutine Exception: ${throwable.message}", throwable)
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO + handler
    private var tempMinNumOfVotes = 0
    private var tempMinRating = 0

    private fun getTempMinValue() = Dependencies.dataStoreMinVotes.data.map { preferences ->
        preferences[SettingsRepo.KEY_MIN_VOTES] ?: 2
    }

    private fun getTempMinRating() = Dependencies.dataStoreMinRating.data.map { preferences ->
        preferences[SettingsRepo.KEY_MIN_RATING] ?: 2
    }

    // Returns Flow with list of movies from database
    fun getMovies(): Flow<List<Movie>> = movieDao.getMovies().map {
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
            val movies = tmdbApiService.getMovies(
                minNumOfVotes = tempMinNumOfVotes,
                minRating = tempMinRating
            )
            // Convert from network to database model
            val convertedList: List<Movie> =
                MovieModelConverter.convertTmdbResultsToListOfMovies(movies)
            // Save converted list to the database
            movieDao.deleteAll()
            movieDao.insertAll(convertedList)
        }
    }

    // Favorites
    fun getMovieFromFavorites(movieId: Int): Flow<MovieFavorite> =
        movieFavoriteDao.getMovie(movieId)

    suspend fun addOrRemoveMovieFromFavorites(movie: Movie, movieInFavorites: Boolean) {
        val movieFavorite: MovieFavorite = MovieModelConverter.convertMovieToMovieFavorite(movie)
        movieFavoriteDao.run {
            if (movieInFavorites) delete(movieFavorite) else insert(movieFavorite)
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "MoviesRepo onCleared")
    }
}