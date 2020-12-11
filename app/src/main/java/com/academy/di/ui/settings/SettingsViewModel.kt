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

    // Min votes
    val minVotesLiveData = settingsRepo.getMinVotes.asLiveData()

    fun onBtnMinusVotesNumClick() {
        viewModelScope.launch {
            val current = minVotesLiveData.value as Int
            if (current > 0) settingsRepo.saveMinVotes(current - 1)
        }
    }

    fun onBtnPlusVotesNumClick() {
        viewModelScope.launch {
            val current = minVotesLiveData.value as Int
            settingsRepo.saveMinVotes(current + 1)
        }
    }

    // Min rating
    val minRatingLiveData = settingsRepo.getMinRating.asLiveData()

    fun onBtnMinusRatingClick() {
        viewModelScope.launch {
            val current = minRatingLiveData.value as Int
            if (current > 0) settingsRepo.saveMinRating(current - 1)
        }
    }

    fun onBtnPlusRatingClick() {
        viewModelScope.launch {
            val current = minRatingLiveData.value as Int
            settingsRepo.saveMinRating(current + 1)
        }
    }

    override fun onCleared() {
        Log.w("Academy", "SettingsViewModel onCleared")
        settingsRepo.onCleared()
    }
}