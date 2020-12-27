package com.academy.ui_favorites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class FavoritesViewModel(private val favoritesRepo: FavoritesRepo) : ViewModel() {
    init {
        Log.w("Academy", "FavoritesViewModel init")
    }

    fun getMovies() = favoritesRepo.getMovies().asLiveData()

    override fun onCleared() {
        favoritesRepo.onCleared()
        Log.w("Academy", "FavoritesViewModel onCleared")
    }
}