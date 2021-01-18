package com.academy.ui_favorites.di

import android.content.Context
import com.academy.db.di.DbDependencies
import com.academy.ui_favorites.FavoritesRepo

// TODO Step 8 - Remove this class
object Dependencies {
    lateinit var appContext: Context
    val favoritesRepo = FavoritesRepo()
    fun getMovieFavoriteDao() = DbDependencies.getMovieFavoriteDao(appContext)
}