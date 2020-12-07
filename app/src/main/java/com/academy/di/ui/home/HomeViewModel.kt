package com.academy.di.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.academy.di.App
import com.academy.di.repo.MoviesRepo
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    @Inject
    lateinit var moviesRepo: MoviesRepo

    init {
        Log.w("Academy", "HomeViewModel init")
        App.component.inject(this)
    }

    var clickedItemPosition = 0

    fun getMovies() = liveData {
        moviesRepo.getMovies().collect {
            if (it.isEmpty()) moviesRepo.fetchFreshMovies()
            emit(it)
        }
    }

    fun onUserRefreshedMain() {
        moviesRepo.fetchFreshMovies()
    }

    override fun onCleared() {
        moviesRepo.onCleared()
        Log.w("Academy", "HomeViewModel onCleared")
    }

    fun saveClickedItemPosition(position: Int?) {
        clickedItemPosition = position ?: 0
    }

}