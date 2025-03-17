package com.senkou.framework.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
   tableName = "sessiones",
   foreignKeys = [ForeignKey(
      entity = MovieEntity::class,
      parentColumns = ["movieId"],
      childColumns = ["movieId"],
      onDelete = ForeignKey.CASCADE
   )],
   indices = [Index(value = ["movieId"])]
)
data class SessionEntity(
   @PrimaryKey val sessionId: String,
   val length: String,
   val spanishReleaseDate: String,
   val hour: String,
   val movieId: Int,
   val roomId: String,
   val roomName: String,
   val format: String,
   val interpreters: String,
   val rating: String,
   val gender: String,
   val synopsis: String,
   val title: String,
   val originalTitle: String,
   val video: String,
   val cartel: String,
   val fullDay: String,
)