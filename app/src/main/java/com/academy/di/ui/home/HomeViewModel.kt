package com.academy.di.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.academy.di.di.Dependencies
import kotlinx.coroutines.flow.collect

class HomeViewModel : ViewModel() {
    init {
        Log.w("Academy", "HomeViewModel init")
    }

    var clickedItemPosition = 0

    fun getMovies() = liveData {
        Dependencies.moviesRepo.getMovies().collect {
            if (it.isEmpty()) Dependencies.moviesRepo.fetchFreshMovies()
            emit(it)
        }
    }

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