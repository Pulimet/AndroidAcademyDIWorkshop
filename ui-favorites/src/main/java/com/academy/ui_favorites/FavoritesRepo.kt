package com.academy.ui_favorites

import android.util.Log
import com.academy.ui_favorites.di.Dependencies

class FavoritesRepo {
    init {
        Log.w("Academy", "FavoritesRepo init")
    }

    fun getMovies() = Dependencies.getMovieFavoriteDao().getMovies()

    fun onCleared() {
        Log.w("Academy", "FavoritesRepo onCleared")
    }
}