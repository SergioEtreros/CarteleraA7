package com.senkou.framework.remote.model

import com.google.gson.annotations.SerializedName

data class Sesion(
   @SerializedName("Duracion")
   val duracion: String,
   @SerializedName("fecha_estreno_spanish")
   val fechaEstrenoSpanish: String,
   @SerializedName("Hora")
   val hora: String,
   @SerializedName("ID_Espectaculo")
   val iDEspectaculo: Int,
   @SerializedName("ID_Pase")
   val iDPase: String,
   @SerializedName("ID_Sala")
   val iDSala: String,
   @SerializedName("NombreSala")
   val nombreSala: String,
   @SerializedName("NombreFormato")
   val nombreFormato: String,
   @SerializedName("Interpretes")
   val interpretes: String,
   @SerializedName("NombreCalificacion")
   val nombreCalificacion: String,
   @SerializedName("NombreGenero")
   val nombreGenero: String,
   @SerializedName("Sinopsis")
   val sinopsis: String,
   @SerializedName("Titulo")
   val titulo: String,
   @SerializedName("TituloOriginal")
   val tituloOriginal: String,
   @SerializedName("Video")
   val video: String,
   @SerializedName("Cartel")
   val cartel: String,
   @SerializedName("diacompleto")
   val diacompleto: String,
)