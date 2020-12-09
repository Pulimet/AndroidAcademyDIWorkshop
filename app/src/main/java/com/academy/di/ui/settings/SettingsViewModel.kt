package com.academy.di.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.academy.di.repo.SettingsRepo
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {
    init {
        Log.w("Academy", "SettingsViewModel init")
    }

    private val settingsRepo = SettingsRepo()

    val minVotesLiveData = settingsRepo.getMinVotes.asLiveData()

    fun onBtnMinusClick() {
        viewModelScope.launch {
            val current = minVotesLiveData.value as Int
            if (current > 1) settingsRepo.saveMinVotes(current - 1)
        }
    }

    fun onBtnPlusClick() {
        viewModelScope.launch {
            val current = minVotesLiveData.value as Int
            settingsRepo.saveMinVotes(current + 1)
        }
    }

    override fun onCleared() {
        Log.w("Academy", "SettingsViewModel onCleared")
        settingsRepo.onCleared()
    }
}