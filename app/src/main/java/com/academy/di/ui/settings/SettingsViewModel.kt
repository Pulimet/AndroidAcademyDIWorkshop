package com.academy.di.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import com.academy.di.di.Injector
import com.academy.di.repo.SettingsRepo

class SettingsViewModel(private val settingsRepo: SettingsRepo) : ViewModel() {
    init {
        Log.w("Academy", "SettingsViewModel init")
    }

    override fun onCleared() {
        Log.w("Academy", "SettingsViewModel onCleared")
        settingsRepo.onCleared()
        Injector.clearSettingsComponent()
    }

    fun kuku() {
        Log.d("Academy", "kuku")
    }
}