package com.academy.di.ui.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.academy.di.R

class DetailsFragment : Fragment(R.layout.details_fragment){
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()
}