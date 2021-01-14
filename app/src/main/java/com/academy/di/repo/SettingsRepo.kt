package com.academy.di.repo

import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import com.academy.di.di.Dependencies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import kotlin.coroutines.CoroutineContext

// TODO Step 2 - Add injection of 'dataStoreVotes' and 'dataStoreRating' to constructor and use them in the class
class SettingsRepo : CoroutineScope {
    init {
        Log.w("Academy", "SettingsRepo init")
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    companion object {
        val KEY_MIN_VOTES = preferencesKey<Int>("minVotes")
        val KEY_MIN_RATING = preferencesKey<Int>("minRating")

        private const val DEFAULT_MIN_VOTES = 5
        private const val DEFAULT_MIN_RATING = 2
    }

    // Min votes
    //TODO: Change dependencies from injected DataStore Votes
    val getMinVotes: Flow<Int> = Dependencies.dataStoreMinVotes.data
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
        Dependencies.dataStoreMinVotes.edit { preferences ->
            preferences[KEY_MIN_VOTES] = minVotes
        }
    }

    // Min rating
    //TODO: Change dependencies from injected DataStore Rating
    val getMinRating: Flow<Int> = Dependencies.dataStoreMinRating.data
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
        Dependencies.dataStoreMinRating.edit { preferences ->
            preferences[KEY_MIN_RATING] = minVotes
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "SettingsRepo onCleared")
    }
}