package com.academy.db.di

import android.content.Context
import androidx.room.Room
import com.academy.db.dao.MovieDao
import com.academy.db.MovieDatabase
import com.academy.db.dao.MovieFavoriteDao

object DbDependencies {
    private lateinit var roomDb: MovieDatabase
    private fun getRoomDb(context: Context): MovieDatabase {
        if (!DbDependencies::roomDb.isInitialized) roomDb =
            Room.databaseBuilder(context, MovieDatabase::class.java, "movies_database").build()
        return roomDb
    }

    fun getMovieFavoriteDao(context: Context): MovieFavoriteDao = getRoomDb(context).movieFavoriteDao()
}