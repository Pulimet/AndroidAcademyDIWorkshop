package com.academy.di.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.di.di.scopes.SettingsScope
import com.academy.di.repo.SettingsRepo
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {
    @Provides
    @SettingsScope
    fun getSettingsRepo(dataStore: DataStore<Preferences>) = SettingsRepo(dataStore)
}