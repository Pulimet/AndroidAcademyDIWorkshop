package com.academy.di.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.academy.db.model.Movie
import com.academy.di.R
import com.academy.di.databinding.FragmentHomeBinding
import com.academy.di.ui.base.BindFragment
import com.academy.di.ui.home.recycler.HomeAdapter
import com.academy.di.ui.home.recycler.OnMovieClickListener
import com.academy.di.ui.navigation.NavigationViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : BindFragment<FragmentHomeBinding>(R.layout.fragment_home),
    OnMovieClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private val navViewModel: NavigationViewModel by activityViewModels()

    private var homeAdapter: HomeAdapter? = null
    private var gridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindIt(FragmentHomeBinding.bind(view))
        setRecyclerView()
        setSwipeRefreshLayout()
        observeViewModel()
    }

    private fun setRecyclerView() {
        Log.w("Academy", "setRecyclerView")
        binding.homeRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2).apply { gridLayoutManager = this }
            adapter = HomeAdapter(this@HomeFragment).apply { homeAdapter = this }

            // Solves return transition animation
            postponeEnterTransition()
        }
    }

    private fun setSwipeRefreshLayout() {
        binding.swipeRefreshLayout.isRefreshing = true
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.onUserRefreshedMain() }
    }

    private fun observeViewModel() {
        viewModel.getMovies().observe(viewLifecycleOwner) {
            homeAdapter?.setItems(it)
            // Scrolls to position of selected item on going back to the list
            scrollToPreviouslyClickedItem(gridLayoutManager)
            if (it.isNotEmpty()) binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun scrollToPreviouslyClickedItem(layoutManager: RecyclerView.LayoutManager?) {
        Log.d("Academy", "If viewModel.savedItemPosition more than 4, scroll to it. (${viewModel.savedItemPosition})")
        lifecycleScope.launch {
            if (viewModel.savedItemPosition > 4) {
                delay(80) // Without this delay scrollToPosition function not working
                layoutManager?.scrollToPosition(viewModel.savedItemPosition)
                delay(10)
                startPostponedEnterTransition()
            } else {
                startPostponedEnterTransition()
            }
        }
    }

    // OnMovieClickListener
    override fun onClick(movie: Movie, extras: FragmentNavigator.Extras, position: Int) {
        viewModel.saveClickedItemPosition(position)
        Log.d("Academy", "Clicked item position saved: $position")
        navViewModel.onUserMovieClick(movie, extras)
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_settings -> {
                viewModel.saveFirstVisiblePosition(gridLayoutManager?.findFirstVisibleItemPosition())
                Log.d(
                    "Academy",
                    "First visible position saved: ${gridLayoutManager?.findFirstVisibleItemPosition()}"
                )
                navViewModel.onSettingsClick()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

}
