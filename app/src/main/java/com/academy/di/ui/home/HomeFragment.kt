package com.academy.di.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.db.model.Movie
import com.academy.di.R
import com.academy.di.ui.navigation.NavigationViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.home_fragment), OnMovieClickListener {
    private val viewModel: HomeViewModel by viewModels()
    private val navViewModel: NavigationViewModel by activityViewModels()

    private var homeAdapter: HomeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setSwipeRefreshLayout()
        observeViewModel()
    }

    private fun setRecyclerView() {
        Log.w("Academy", "setRecyclerView")
        homeRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            homeAdapter = HomeAdapter(this@HomeFragment)
            adapter = homeAdapter

            // Scrolls to position of selected item on going back to the list
            scrollToPreviouslyClickedItem(layoutManager)

            // Solves return transition animation
            postponeAndStartTransitionAnimation()
        }
    }

    private fun RecyclerView.postponeAndStartTransitionAnimation() {
        postponeEnterTransition()
        viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    private fun scrollToPreviouslyClickedItem(layoutManager: RecyclerView.LayoutManager?) {
        lifecycleScope.launch {
            if (viewModel.clickedItemPosition > 4) {
                delay(20) // Without this delay scrollToPosition function not working
                layoutManager?.scrollToPosition(viewModel.clickedItemPosition)
            }
        }
    }

    private fun setSwipeRefreshLayout() {
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener { viewModel.onUserRefreshedMain() }
    }

    private fun observeViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            homeAdapter?.setItems(it)
            if (it.isNotEmpty()) swipeRefreshLayout.isRefreshing = false
        }
    }

    //OnMovieClickListener
    override fun onClick(movie: Movie, extras: FragmentNavigator.Extras, position: Int) {
        viewModel.saveClickedItemPosition(position)
        navViewModel.onUserMovieClick(movie, extras)
    }
}
