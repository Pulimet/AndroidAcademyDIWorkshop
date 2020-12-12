package com.academy.di.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataStoreModule(private val context: Context) {
    @Provides
    @Singleton
    @Named("Votes")
    fun getDataStoreVotes(): DataStore<Preferences> = context.createDataStore("DiWorkshopVotes")

    @Provides
    @Singleton
    @Named("Rating")
    fun getDataStoreRating(): DataStore<Preferences> = context.createDataStore("DiWorkshopRating")
}