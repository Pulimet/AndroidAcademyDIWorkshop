package com.academy.db.model

import com.academy.network.model.TmdbNet

object MovieModelConverter {
    private const val TMDB_IMG_URL = "https://image.tmdb.org/t/p/w500"

    fun convert(movies: TmdbNet.Discover): List<Movie> {
        return movies.results.map {
            Movie(
                id = it.id,
                title = it.title,
                posterUrl = getImageUrl(it),
                overview = it.overview,
                date = it.date,
                vote = it.vote
            )
        }
    }

    private fun getImageUrl(movie: TmdbNet.Movie) =
        when {
            movie.posterImg != null -> TMDB_IMG_URL + movie.posterImg
            movie.backImg != null -> TMDB_IMG_URL + movie.backImg
            else -> null
        }
}