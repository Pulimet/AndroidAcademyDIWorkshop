package com.academy.di.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.academy.db.model.Movie
import com.academy.di.ui.navigation.NavigationViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MovieHolder(override val containerView: View, private val viewModel: NavigationViewModel) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer,
    View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    private var movie: Movie? = null

    fun onBindViewHolder(movie: Movie) {
        this.movie = movie
        movie.posterUrl?.let { imgMovie.load(it) {
            crossfade(true)
            scale(Scale.FILL)
        } }
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            movie?.let { viewModel.onUserMovieClick(it) }
        }
    }
}