package com.academy.ui_favorites.di

import com.academy.navigation.BaseFavoritesComponent
import com.academy.navigation.DiHolder

// TODO Step 5 - Make this interface a Dagger 2 @SubComponent with  @Subcomponent.Builder
// TODO Step 5 - Allow injecting for FavoritesFragment and FavoritesRepo
interface FavoritesComponent: BaseFavoritesComponent {


    companion object {
        fun getFavoriteComponent() =
            DiHolder.baseInjector.getFavoritesComponent() as FavoritesComponent
    }
}