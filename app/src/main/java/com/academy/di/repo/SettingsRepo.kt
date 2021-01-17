package com.academy.di.repo

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import com.academy.di.di.Injector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class SettingsRepo @Inject constructor() : CoroutineScope {
    @Inject
    @Named("Votes")
    lateinit var dataStoreVotes: DataStore<Preferences>

    @Inject
    @Named("Rating")
    lateinit var dataStoreRating: DataStore<Preferences>

    init {
        Log.w("Academy", "SettingsRepo init")
        Injector.appComponent.inject(this)
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    companion object {
        val KEY_MIN_VOTES = preferencesKey<Int>("minVotes")
        val KEY_MIN_RATING = preferencesKey<Int>("minRating")

        private const val DEFAULT_MIN_VOTES = 5
        private const val DEFAULT_MIN_RATING = 2
    }

    // Min votes
    val getMinVotes: Flow<Int> = dataStoreVotes.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[KEY_MIN_VOTES] ?: DEFAULT_MIN_VOTES
        }


    suspend fun saveMinVotes(minVotes: Int) {
        dataStoreVotes.edit { preferences ->
            preferences[KEY_MIN_VOTES] = minVotes
        }
    }

    // Min rating
    val getMinRating: Flow<Int> = dataStoreRating.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[KEY_MIN_RATING] ?: DEFAULT_MIN_RATING
        }


    suspend fun saveMinRating(minVotes: Int) {
        dataStoreRating.edit { preferences ->
            preferences[KEY_MIN_RATING] = minVotes
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "SettingsRepo onCleared")
    }
}