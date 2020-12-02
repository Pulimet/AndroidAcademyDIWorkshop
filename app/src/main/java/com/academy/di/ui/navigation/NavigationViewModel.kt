package com.academy.di.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.academy.db.model.Movie
import com.academy.di.ui.home.HomeFragmentDirections
import com.academy.di.utils.SingleLiveEvent

class NavigationViewModel: ViewModel() {
    private var navEvent = SingleLiveEvent<NavDirections>()
    fun getNavEvent(): LiveData<NavDirections> = navEvent

    fun onUserMovieClick(movie: Movie) {
        navEvent.value = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(movie)
    }
}