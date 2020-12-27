package com.academy.navigation

// TODO Step 5 - Nothing TODO, just note that we use this object to save the Injector reference
object DiHolder {
    lateinit var baseInjector: BaseInjector
}

// TODO Step 5 - Nothing TODO, just note that we need this interface to build FavoritesComponent from other modules (not 'app')
interface BaseInjector {
    fun getFavoritesComponent(): BaseFavoritesComponent
    fun clearFavoritesComponent()
}

// TODO Step 5 - Nothing TODO, just note that we need interface like this for each Component outside our 'app' module
interface BaseFavoritesComponent