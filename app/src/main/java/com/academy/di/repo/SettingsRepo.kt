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

class SettingsRepo @Inject constructor(
    @Named("Votes") private val dataStoreMinVotes: DataStore<Preferences>,
    @Named("Rating") private val dataStoreMinRating: DataStore<Preferences>
) : CoroutineScope {
    init {
        Log.w("Academy", "SettingsRepo init")
        Injector.appComponent.inject(this)
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    companion object {
        val KEY_MIN_VOTES = preferencesKey<Int>("minVotes")
        val KEY_MIN_RATING = preferencesKey<Int>("minRating")
    }

    // Min votes
    val getMinVotes: Flow<Int> = dataStoreMinVotes.data
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
        dataStoreMinVotes.edit { preferences ->
            preferences[KEY_MIN_VOTES] = minVotes
        }
    }

    // Min rating
    val getMinRating: Flow<Int> = dataStoreMinRating.data
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
        dataStoreMinRating.edit { preferences ->
            preferences[KEY_MIN_RATING] = minVotes
        }
    }

    fun onCleared() {
        coroutineContext.cancelChildren()
        Log.w("Academy", "SettingsRepo onCleared")
    }
}