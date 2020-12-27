package com.academy.ui_favorites

import android.util.Log
import com.academy.db.dao.MovieFavoriteDao
import javax.inject.Inject

class FavoritesRepo  @Inject constructor(private val movieFavoriteDao: MovieFavoriteDao) {
    init {
        Log.w("Academy", "FavoritesRepo init")
    }

    fun getMovies() = movieFavoriteDao.getMovies()

    fun onCleared() {
        Log.w("Academy", "FavoritesRepo onCleared")
    }
}