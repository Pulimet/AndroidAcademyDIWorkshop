package com.academy.di.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/*  TODO Step 1 - Change DataStoreModule such that it will provide two instances of DataStore by using @Named annotation
     One instance for "Votes" and second one for "Rating"*/
@Module
class DataStoreModule(private val context: Context) {
    @Provides
    @Singleton
    fun getDataStoreVotes(): DataStore<Preferences> = context.createDataStore("DiWorkshopVotes")
}