package com.academy.di.di.modules

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.academy.di.repo.SettingsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SettingsModule {
    // TODO Step 3 - Change scope from @Singleton to  @SettingsScope

    @Provides
    @Singleton
    fun getSettingsRepo(
        @Named("Votes") dataStoreVotes: DataStore<Preferences>,
        @Named("Rating") dataStoreRating: DataStore<Preferences>
    ) = SettingsRepo(dataStoreVotes, dataStoreRating)
}