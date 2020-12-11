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

class SettingsRepo : CoroutineScope {
    init {
        Log.w("Academy", "SettingsRepo init")
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    companion object {
        val KEY_MIN_VOTES = preferencesKey<Int>("minVotes")
        val KEY_MIN_RATING = preferencesKey<Int>("minRating")
    }

    // Min votes
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
            preferences[KEY_MIN_VOTES] ?: 2
        }


    suspend fun saveMinVotes(minVotes: Int) {
        Dependencies.dataStoreMinVotes.edit { preferences ->
            preferences[KEY_MIN_VOTES] = minVotes
        }
    }

    // Min rating
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
            preferences[KEY_MIN_RATING] ?: 2
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