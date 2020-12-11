package com.academy.di.di

import androidx.datastore.preferences.createDataStore

object Dependencies {
    val dataStore = App.applicationContext().createDataStore("DiWorkshop")
}