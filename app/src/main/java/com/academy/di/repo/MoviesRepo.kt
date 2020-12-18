package com.academy.di.repo

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.db.MovieDao
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite
import com.academy.db.utils.MovieModelConverter
import com.academy.di.di.Injector
import com.academy.di.example.LogOnCreationDemo
import com.academy.network.services.TmdbApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class MoviesRepo @Inject constructor(
    private val movieDao: MovieDao,
    private val tmdbApiService: TmdbApiService,
    @Named("Votes") private val dataStoreVotes: DataStore<Preferences>,
    @Named("Rating") private val dataStoreRating: DataStore<Preferences>,
    logOnCreationDemo: LogOnCreationDemo
) : CoroutineScope {

    init {
        Log.w("Academy", "MoviesRepo init")
        Injector.appComponent.inject(this)

        logOnCreationDemo.play()
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO
    private var tempMinNumOfVotes = 0
    private var tempMinRating = 0

    private fun getTempMinValue() = dataStoreVotes.data.map { preferences ->
        preferences[SettingsRepo.KEY_MIN_VOTES] ?: 2
    }

    private fun getTempMinRating() = dataStoreRating.data.map { preferences ->
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