package com.academy.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val overview: String,
    val date: String,
    val vote: Double,
    val voteCount: Int
): Parcelable {
    fun getTitleWithYear() = title + " (" + getYear() + ")"

    private fun getYear(): String {
        val parsedDate = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(date)
        val formatOut = SimpleDateFormat("yyyy", Locale.US)
        return if (parsedDate != null) {
            formatOut.format(parsedDate)
        } else {
            ""
        }
    }
}
