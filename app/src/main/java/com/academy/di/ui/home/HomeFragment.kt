package com.academy.di.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.academy.di.R
import com.academy.di.ui.navigation.NavigationViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {
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
        homeRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            homeAdapter = HomeAdapter(navViewModel)
            adapter = homeAdapter
        }
    }

    private fun setSwipeRefreshLayout() {
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setOnRefreshListener { viewModel.onUserRefreshedMain() }
    }

    private fun observeViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            homeAdapter?.setItems(it)
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
