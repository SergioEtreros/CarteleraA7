package com.senkou.carteleraa7.data.model


import com.google.gson.annotations.SerializedName
import com.senkou.carteleraa7.data.Utilidades
import java.util.*

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
) {
   fun getUrlCartel(): String = Utilidades.RUTA_CARTELES + cartel

   fun crearDetalles(): ArrayList<String> {

      val textoFicha: ArrayList<String> = ArrayList()
      textoFicha.add("")
      textoFicha.add("Título original: $tituloOriginal")
      textoFicha.add("Duración: $duracion")
//        textoFicha.add("Director: $director")
      textoFicha.add("Género: $nombreGenero")
      textoFicha.add("Calificación: $nombreCalificacion")
      textoFicha.add("Reparto: $interpretes")
      textoFicha.add("Fecha de estreno: $fechaEstrenoSpanish")
      textoFicha.add(sinopsis)

      return textoFicha
   }

   fun crearTextoSesiones(sesiones: List<Sesion>): String {

      val sesions = ArrayList<Sala>()
      var textoSesionesHoy = ""

      sesiones.forEach { sesion ->
         val sala = sesions.find { s -> s.sala == sesion.iDSala }
         if (sala != null) {
            sala.horas += " - " + sesion.hora
         } else {
            val formato = "(${sesion.nombreFormato})"
            sesions.add(
               Sala(
                  sesion.nombreSala,
                  "${formato.takeIf { it.contains("3D") } ?: ""} ${sesion.hora}"
               )
            )
         }
      }

      sesions.forEachIndexed { i, salas ->
         textoSesionesHoy += if (i == (sesions.size - 1))
            salas.sala.replace("0", "") + " : " + salas.horas
         else
            salas.sala.replace("0", "") + " : " + salas.horas + "\n"
      }

      return textoSesionesHoy
   }
}