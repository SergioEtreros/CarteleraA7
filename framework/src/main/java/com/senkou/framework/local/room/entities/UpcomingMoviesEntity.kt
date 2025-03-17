package com.senkou.framework.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
   tableName = "upcoming",
)
data class UpcomingMoviesEntity(
   @PrimaryKey val movieId: Int,
   val cartel: String,
   val releaseDate: String,
   val title: String,
   val originalTitle: String,
   val background: String,
)