package com.academy.di.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.di.di.Dependencies

class HomeViewModel : ViewModel() {
    init {
        Log.w("Academy", "HomeViewModel init")
    }

    var clickedItemPosition = 0

    fun getMovies() = Dependencies.moviesRepo.getMovies().asLiveData()

    fun onUserRefreshedMain() {
        Dependencies.moviesRepo.fetchFreshMovies()
    }

    override fun onCleared() {
        Dependencies.moviesRepo.onCleared()
        Log.w("Academy", "HomeViewModel onCleared")
    }

    fun saveClickedItemPosition(position: Int?) {
        clickedItemPosition = position ?: 0
    }

}