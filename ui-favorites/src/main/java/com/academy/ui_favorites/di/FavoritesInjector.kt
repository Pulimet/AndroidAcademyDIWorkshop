package com.academy.ui_favorites.di

// TODO: Nothing TODO, just note that we need this interface to build FavoritesComponent
interface FavoritesInjector {
    fun getFavoritesComponent(): FavoritesComponent
    fun clearFavoritesComponent()
}