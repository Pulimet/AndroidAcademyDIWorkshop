package com.academy.ui_favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.ui_favorites.di.Dependencies

class FavoritesViewModel : ViewModel() {
    init {
        Log.w("Academy", "FavoritesViewModel init")
    }

    fun getMovies() = Dependencies.favoritesRepo.getMovies().asLiveData()

    override fun onCleared() {
        Dependencies.favoritesRepo.onCleared()
        Log.w("Academy", "FavoritesViewModel onCleared")
    }
}