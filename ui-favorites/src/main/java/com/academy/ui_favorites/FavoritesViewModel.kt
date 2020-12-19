package com.academy.ui_favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.ui_favorites.di.Dependencies

class FavoritesViewModel: ViewModel(){
    init {
        Log.w("Academy", "FavoritesViewModel init")
    }

    var savedItemPosition = 0

    fun getMovies() = Dependencies.getMovieFavoriteDao().getMovies().asLiveData()

    override fun onCleared() {
        Log.w("Academy", "FavoritesViewModel onCleared")
    }
}