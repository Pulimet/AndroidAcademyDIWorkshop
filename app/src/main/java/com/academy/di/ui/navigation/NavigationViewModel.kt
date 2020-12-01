package com.academy.di.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.academy.di.ui.home.HomeFragmentDirections
import com.academy.di.utils.SingleLiveEvent

class NavigationViewModel: ViewModel() {
    private var navEvent = SingleLiveEvent<NavDirections>()
    fun getNavEvent(): LiveData<NavDirections> = navEvent

    fun onBtnOpenDetailsClick() {
        navEvent.value = HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
    }
}