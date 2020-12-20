package com.academy.ui_favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.db.dao.MovieFavoriteDao
import javax.inject.Inject

class FavoritesViewModelFactory @Inject constructor(private val movieFavoriteDao: MovieFavoriteDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FavoritesViewModel(movieFavoriteDao) as T
    }
}