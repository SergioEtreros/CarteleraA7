package com.senkou.domain.model

data class Sesion(
   val duracion: String,
   val fechaEstrenoSpanish: String,
   val hora: String,
   val idEspectaculo: Int,
   val idPase: String,
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
   val background: String = ""
)