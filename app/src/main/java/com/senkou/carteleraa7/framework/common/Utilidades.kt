package com.senkou.carteleraa7.framework.common

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.senkou.carteleraa7.domain.Sala
import com.senkou.carteleraa7.domain.Sesion
import java.text.SimpleDateFormat
import java.util.*

class Utilidades {
   companion object {

      const val RUTA_CARTELES = "https://artesiete.es/Posters/"
      const val HOY = "Hoy"

      fun playTrailer(context: Context, urlTrailer: String) {
         when {
            urlTrailer.contains("youtu.be") -> {
               val videoId = urlTrailer.substringAfter("youtu.be/")
               openYoutubeLink(context, videoId)
            }

            urlTrailer.contains("youtube") -> {

               val videoId = urlTrailer.substringAfter("v=")
               openYoutubeLink(context, videoId)
            }

            else -> {
               Toast.makeText(context, "No se reconoce la ID del vídeo", Toast.LENGTH_SHORT).show()
            }
         }
      }

      private fun openYoutubeLink(context: Context, youtubeID: String) {

         val id = if (youtubeID.contains("youtube")) {
            youtubeID.substring(youtubeID.lastIndexOf("/") + 1)
         } else {
            youtubeID
         }

         val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
         val intentBrowser =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$id"))
         try {
            context.startActivity(intentApp)
         } catch (ex: ActivityNotFoundException) {
            context.startActivity(intentBrowser)
         }
      }

      fun ordenarMeses(fechasSpinner: ArrayList<String>) {

         if (fechasSpinner.isNotEmpty()) {
            fechasSpinner.sort()
            val primero = fechasSpinner.first()

            val index = fechasSpinner.indexOf(
               fechasSpinner.find { element ->
                  element.substring(element.indexOf('/') + 1, element.lastIndexOf('/')) <
                        primero.substring(primero.indexOf('/') + 1, primero.lastIndexOf('/'))
               })

            for (i in 1..index) {
               fechasSpinner.add(fechasSpinner.first())
               fechasSpinner.removeFirst()
            }
         }
      }

      fun getDateFormatter() = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

      fun getUrlCartel(cartel: String): String = RUTA_CARTELES + cartel

      fun crearDetalles(sesion: Sesion): ArrayList<String> {

         val textoFicha: ArrayList<String> = ArrayList()
         textoFicha.add("")
         textoFicha.add("Título original: ${sesion.tituloOriginal}")
         textoFicha.add("Duración: ${sesion.duracion}")
//        textoFicha.add("Director: ${sesion.director}")
         textoFicha.add("Género: ${sesion.nombreGenero}")
         textoFicha.add("Calificación: ${sesion.nombreCalificacion}")
         textoFicha.add("Reparto: ${sesion.interpretes}")
         textoFicha.add("Fecha de estreno: ${sesion.fechaEstrenoSpanish}")
         textoFicha.add(sesion.sinopsis)

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
}