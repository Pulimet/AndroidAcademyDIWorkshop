package com.academy.ui_favorites

import android.util.Log
import com.academy.db.dao.MovieFavoriteDao
import com.academy.ui_favorites.di.FavoritesComponent
import javax.inject.Inject

class FavoritesRepo  @Inject constructor(private val movieFavoriteDao: MovieFavoriteDao) {
    init {
        Log.w("Academy", "FavoritesRepo init")
        FavoritesComponent.getFavoriteComponent().inject(this)
    }

    fun getMovies() = movieFavoriteDao.getMovies()

    fun onCleared() {
        Log.w("Academy", "FavoritesRepo onCleared")
    }
}