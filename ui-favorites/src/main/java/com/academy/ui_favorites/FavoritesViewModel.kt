package com.academy.ui_favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.db.dao.MovieFavoriteDao

class FavoritesViewModel(private val movieFavoriteDao: MovieFavoriteDao) : ViewModel() {
    init {
        Log.w("Academy", "FavoritesViewModel init")
    }

    var savedItemPosition = 0

    fun getMovies() = movieFavoriteDao.getMovies().asLiveData()

    override fun onCleared() {
        Log.w("Academy", "FavoritesViewModel onCleared")
    }
}