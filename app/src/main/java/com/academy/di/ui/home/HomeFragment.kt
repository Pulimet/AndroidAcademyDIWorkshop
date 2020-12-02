package com.academy.di.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.db.model.Movie
import com.academy.di.R
import com.academy.di.ui.navigation.NavigationViewModel
import kotlinx.android.synthetic.main.home_fragment.*

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

            // Solves return transition animation
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
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
    override fun onClick(movie: Movie, extras: FragmentNavigator.Extras) {
       navViewModel.onUserMovieClick(movie, extras)
    }
}
