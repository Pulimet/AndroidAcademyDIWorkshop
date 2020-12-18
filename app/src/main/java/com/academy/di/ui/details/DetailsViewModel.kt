package com.academy.di.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.academy.db.model.Movie
import com.academy.db.model.MovieFavorite
import com.academy.di.di.Dependencies
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private var isMovieInFavorites = false

    fun getMovieFromFavorites(movieId: Int): LiveData<Boolean> =
        Dependencies.moviesRepo.getMovieFromFavorites(movieId)
            .map { movieFavorite: MovieFavorite? ->
                isMovieInFavorites = movieFavorite != null
                isMovieInFavorites
            }.asLiveData()

    fun onFavoriteImageClick(movieId: Movie) {
        viewModelScope.launch {
            Dependencies.moviesRepo.addOrRemoveMovieFromFavorites(movieId, isMovieInFavorites)
        }
    }
}