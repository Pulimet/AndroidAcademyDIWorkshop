package com.academy.di.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataStoreModule(private val context: Context) {
    @Provides
    @Singleton
    fun getDataStore(): DataStore<Preferences> = context.createDataStore("DiWorkshop")
}