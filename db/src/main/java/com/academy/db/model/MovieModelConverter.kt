package com.academy.db.model

import com.academy.network.model.TmdbNet

object MovieModelConverter {
    fun convert(movies: TmdbNet.Discover): List<Movie> {
        return movies.results.map {
            Movie(
                id = it.id,
                title = it.title,
                posterImg = it.posterImg,
                backImg = it.backImg,
                overview = it.overview,
                date = it.date,
                vote = it.vote
            )
        }
    }

}