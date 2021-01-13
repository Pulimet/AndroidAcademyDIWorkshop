package com.academy.ui_favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.ui_favorites.di.DiHolder

class FavoritesViewModel(private val favoritesRepo: FavoritesRepo) : ViewModel() {
    init {
        Log.w("Academy", "FavoritesViewModel init")
    }

    fun getMovies() = favoritesRepo.getMovies().asLiveData()

    override fun onCleared() {
        favoritesRepo.onCleared()
        // TODO Step 5 - Nothing TODO, just note it was modified and FavoritesComponent cleared
        DiHolder.favoritesInjector.clearFavoritesComponent()
        Log.w("Academy", "FavoritesViewModel onCleared")
    }
}