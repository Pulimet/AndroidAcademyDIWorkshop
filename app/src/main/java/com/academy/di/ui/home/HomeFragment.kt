package com.academy.di.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.academy.di.R
import com.academy.di.ui.navigation.NavigationViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment(R.layout.home_fragment) {
    private val viewModel: HomeViewModel by viewModels()
    private val navViewModel: NavigationViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovies().observe(viewLifecycleOwner) {
            button.text = it.firstOrNull()?.title ?: "null"
        }

        button.setOnClickListener {
            navViewModel.onBtnOpenDetailsClick()
        }
    }
}
