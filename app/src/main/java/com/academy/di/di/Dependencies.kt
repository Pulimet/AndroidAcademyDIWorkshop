package com.academy.di.di

import com.academy.di.App

// TODO Step 11 - Remove this file and inject its dependencies with Dagger into constructors of MoviesRepo and properties of SettingsRepo.
object Dependencies {
    val dataStoreMinVotes = App.applicationContext().createDataStore("DiWorkshopVotes")
    val dataStoreMinRating = App.applicationContext().createDataStore("DiWorkshopRating")
}