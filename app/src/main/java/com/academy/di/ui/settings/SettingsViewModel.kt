package com.academy.di.ui.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import com.academy.di.di.Injector
import com.academy.di.repo.SettingsRepo
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepo: SettingsRepo) : ViewModel() {
    init {
        Log.w("Academy", "SettingsViewModel init")
    }

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
        Injector.clearSettingsComponent()
    }
}