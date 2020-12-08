package com.academy.di.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import com.academy.di.repo.SettingsRepo

class SettingsViewModel : ViewModel() {
    init {
        Log.w("Academy", "SettingsViewModel init")
    }

    private val settingsRepo = SettingsRepo()

    override fun onCleared() {
        Log.w("Academy", "SettingsViewModel onCleared")
        settingsRepo.onCleared()
    }
}