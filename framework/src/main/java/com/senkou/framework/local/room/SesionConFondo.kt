package com.senkou.framework.local.room

import androidx.room.Embedded
import androidx.room.Relation
import com.senkou.framework.local.room.entities.PeliculaEntity
import com.senkou.framework.local.room.entities.SesionEntity

data class SesionConFondo(
   @Embedded
   val sesion: SesionEntity,

   @Relation(
      parentColumn = "idEspectaculo",
      entityColumn = "idEspectaculo",
      entity = PeliculaEntity::class,
      projection = ["background"]
   )
   val fondo: String
)
