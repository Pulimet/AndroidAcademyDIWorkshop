package com.academy.di.ui.home.recycler

import android.view.View
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.academy.db.model.Movie
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*

class MovieHolder(override val containerView: View, private val listener: OnMovieClickListener) :
    RecyclerView.ViewHolder(containerView),
    LayoutContainer,
    View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    private var movie: Movie? = null

    fun onBindViewHolder(movie: Movie) {
        this.movie = movie
        movie.posterUrl?.let {
            imgMovie.load(it) {
                crossfade(true)
                scale(Scale.FILL)
            }
        }
        val votesText = "${movie.vote} (${movie.voteCount})"
        tvVotes.text = votesText
        ViewCompat.setTransitionName(imgMovie, "image_${movie.id}")
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        if (adapterPosition != RecyclerView.NO_POSITION) {
            val extras = FragmentNavigatorExtras(
                imgMovie to "image_${movie?.id}"
            )
            movie?.let { listener.onClick(it, extras, adapterPosition) }
        }
    }
}