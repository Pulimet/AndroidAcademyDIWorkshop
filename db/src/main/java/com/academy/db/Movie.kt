package com.academy.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterImg: String?,
    val backImg: String?,
    val overview: String,
    val date: String,
    val vote: Double
)
