package com.academy.ui_favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

// TODO Step 5 - Nothing TODO, just note it was added
class FavoritesViewModelFactory @Inject constructor(private val favoritesRepo: FavoritesRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return FavoritesViewModel(favoritesRepo) as T
    }
}