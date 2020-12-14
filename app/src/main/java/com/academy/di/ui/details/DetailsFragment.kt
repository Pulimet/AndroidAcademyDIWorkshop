package com.academy.di.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import com.academy.di.R
import com.academy.di.databinding.FragmentDetailsBinding
import com.academy.di.ui.base.BindFragment

class DetailsFragment : BindFragment<FragmentDetailsBinding>(R.layout.fragment_details) {
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindIt(FragmentDetailsBinding.bind(view))
        fillMovieData()
        ViewCompat.setTransitionName(binding.imgMoviePoster, "image_${args.movie.id}")
    }

    private fun fillMovieData() {
        args.movie.let {
            binding.imgMoviePoster.load(it.posterUrl)
            binding.tvTitle.text = it.getTitleWithYear()
            binding.tvDescription.text = it.overview
            binding.tvRating.text = String.format("Rating: %s", it.vote.toString())
        }
    }
}