package com.academy.di.di

import androidx.datastore.preferences.createDataStore
import com.academy.di.App

// TODO Step 2 - Remove this file and inject its dependencies with Dagger into constructors of MoviesRepo and SettingsRepo.
object Dependencies {
    val dataStoreMinVotes = App.applicationContext().createDataStore("DiWorkshopVotes")
    val dataStoreMinRating = App.applicationContext().createDataStore("DiWorkshopRating")
}