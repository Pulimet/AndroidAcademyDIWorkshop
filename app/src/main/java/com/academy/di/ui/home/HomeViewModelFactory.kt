package com.academy.di.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.di.repo.MoviesRepo
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val moviesRepo: MoviesRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return HomeViewModel(moviesRepo) as T
    }
}