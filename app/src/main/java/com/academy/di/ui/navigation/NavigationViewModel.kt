package com.academy.di.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.academy.db.model.Movie
import com.academy.di.ui.home.HomeFragmentDirections
import com.academy.di.utils.SingleLiveEvent

class NavigationViewModel : ViewModel() {
    private var navEvent = SingleLiveEvent<Pair<NavDirections, FragmentNavigator.Extras>>()
    fun getNavEvent(): LiveData<Pair<NavDirections, FragmentNavigator.Extras>> = navEvent

    fun onUserMovieClick(movie: Movie, extras: FragmentNavigator.Extras) {
        navEvent.value =
            Pair(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie), extras)
    }

    fun onSettingsClick() {
        navEvent.value = Pair(HomeFragmentDirections.actionHomeFragmentToSettingsFragment(), FragmentNavigatorExtras())
    }
}