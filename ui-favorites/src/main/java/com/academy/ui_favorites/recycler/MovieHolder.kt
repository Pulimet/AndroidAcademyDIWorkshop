package com.academy.ui_favorites.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.academy.db.model.Movie
import com.academy.ui_favorites.databinding.ItemMovieBinding

class MovieHolder(v: View) : RecyclerView.ViewHolder(v) {

    private val binding = ItemMovieBinding.bind(v)
    private var movie: Movie? = null

    fun onBindViewHolder(movie: Movie) {
        this.movie = movie
        setVotes(movie)
        loadImage(movie)
    }

    private fun setVotes(movie: Movie) {
        val votesText = "${movie.vote} (${movie.voteCount})"
        binding.tvVotes.apply {
            visibility = View.GONE
            text = votesText
        }
    }

    private fun loadImage(movie: Movie) {
        movie.posterUrl?.let {
            binding.imgMovie.load(it) {
                crossfade(true)
                scale(Scale.FILL)
                listener(onSuccess = { _, _ ->
                    binding.tvVotes.visibility = View.VISIBLE
                })
            }
        }
    }
}