package com.senkou.framework.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
   tableName = "peliculas",
)
data class PeliculaEntity(
   @PrimaryKey val idEspectaculo: Int,
   val cartel: String,
   val fechaEstreno: String,
   val titulo: String,
   val tituloOriginal: String,
   val background: String,
)

