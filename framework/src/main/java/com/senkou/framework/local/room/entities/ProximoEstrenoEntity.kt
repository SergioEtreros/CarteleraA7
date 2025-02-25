package com.senkou.framework.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
   tableName = "estrenos",
)
data class ProximoEstrenoEntity(
   @PrimaryKey val idEspectaculo: Int,
   val cartel: String,
   val fechaEstreno: String,
   val titulo: String,
   val tituloOriginal: String,
   val background: String,
)