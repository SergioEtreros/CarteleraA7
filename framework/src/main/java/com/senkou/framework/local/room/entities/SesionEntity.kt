package com.senkou.framework.local.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
   tableName = "sesiones",
   foreignKeys = [ForeignKey(
      entity = PeliculaEntity::class,
      parentColumns = ["idEspectaculo"],
      childColumns = ["idEspectaculo"],
      onDelete = ForeignKey.CASCADE
   )],
   indices = [Index(value = ["idEspectaculo"])]
)
data class SesionEntity(
   @PrimaryKey val idPase: String,
   val duracion: String,
   val fechaEstrenoSpanish: String,
   val hora: String,
   val idEspectaculo: Int,
   val idSala: String,
   val nombreSala: String,
   val nombreFormato: String,
   val interpretes: String,
   val nombreCalificacion: String,
   val nombreGenero: String,
   val sinopsis: String,
   val titulo: String,
   val tituloOriginal: String,
   val video: String,
   val cartel: String,
   val diacompleto: String,
)