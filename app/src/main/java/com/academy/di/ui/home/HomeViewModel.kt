package com.academy.di.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.academy.di.di.Dependencies

class HomeViewModel : ViewModel() {
    fun getMovies() = Dependencies.getMoviesRepo().getMovies().asLiveData()

    fun onUserRefreshedMain() {
        Dependencies.getMoviesRepo().fetchFreshMovies()
    }

    override fun onCleared() {
        Dependencies.getMoviesRepo().onCleared()
    }

}