package com.academy.di.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import coil.load
import com.academy.di.R
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment(R.layout.details_fragment) {
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillMovieData()
        ViewCompat.setTransitionName(imgMoviePoster, "image_${args.movie.id}")
    }

    private fun fillMovieData() {
        args.movie.let {
            imgMoviePoster.load(it.posterUrl)
            tvTitle.text = it.getTitleWithYear()
            tvDescription.text = it.overview
            tvRating.text = String.format("Rating: %s", it.vote.toString())
        }
    }
}