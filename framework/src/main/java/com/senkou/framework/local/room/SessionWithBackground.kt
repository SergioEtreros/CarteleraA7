package com.senkou.framework.local.room

import androidx.room.Embedded
import androidx.room.Relation
import com.senkou.framework.local.room.entities.MovieEntity
import com.senkou.framework.local.room.entities.SessionEntity

data class SessionWithBackground(
   @Embedded
   val session: SessionEntity,

   @Relation(
      parentColumn = "movieId",
      entityColumn = "movieId",
      entity = MovieEntity::class,
      projection = ["background"]
   )
   val background: String
)
