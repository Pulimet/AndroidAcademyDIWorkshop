package com.academy.di.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.di.App
import com.academy.di.repo.MoviesRepo
import javax.inject.Inject

class HomeViewModel(private val moviesRepo: MoviesRepo) : ViewModel() {
    init {
        Log.w("Academy", "HomeViewModel init")
    }

    var savedItemPosition = 0

    fun getMovies() = moviesRepo.getMovies().asLiveData()

    fun onUserRefreshedMain() {
        moviesRepo.fetchFreshMovies()
    }

    override fun onCleared() {
        moviesRepo.onCleared()
        Log.w("Academy", "HomeViewModel onCleared")
    }

    fun saveClickedItemPosition(position: Int?) {
        savedItemPosition = position ?: 0
    }

    fun saveFirstVisiblePosition(position: Int?) {
        savedItemPosition = position ?: 0
    }

}