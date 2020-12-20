package com.academy.di.di

import androidx.datastore.preferences.createDataStore
import com.academy.di.App

object Dependencies {
    val dataStoreMinVotes = App.applicationContext().createDataStore("DiWorkshopVotes")
    val dataStoreMinRating = App.applicationContext().createDataStore("DiWorkshopRating")
}