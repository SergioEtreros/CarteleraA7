package com.senkou.domain.common

import com.senkou.domain.model.Sala
import com.senkou.domain.model.Sesion
import java.text.SimpleDateFormat
import java.util.*

fun ArrayList<String>.ordenarMeses() {

   if (this.isNotEmpty()) {
      this.sort()
      val primero = this.first()

      val index = this.indexOf(
         this.find { element ->
            element.substring(element.indexOf('/') + 1, element.lastIndexOf('/')) <
                  primero.substring(primero.indexOf('/') + 1, primero.lastIndexOf('/'))
         })

      for (i in 1..index) {
         this.add(this.first())
         this.removeAt(0)
      }
   }
}

fun Sesion.crearDetalles(): ArrayList<String> {

   val textoFicha: ArrayList<String> = ArrayList()
   textoFicha.add("")
   textoFicha.add("Título original:\n${this.tituloOriginal}")
   textoFicha.add("Duración:\n${this.duracion}")
//        textoFicha.add("Director: ${this.director}")
   textoFicha.add("Género:\n${this.nombreGenero}")
   textoFicha.add("Calificación:\n${this.nombreCalificacion}")
   textoFicha.add("Reparto:\n${this.interpretes}")
   textoFicha.add("Fecha de estreno:\n${this.fechaEstrenoSpanish}")
   textoFicha.add(this.sinopsis)

   return textoFicha
}

fun List<Sesion>.crearTextoSesiones(): String {

   val sesions = ArrayList<Sala>()
   var textoSesionesHoy = ""

   this.forEach { sesion ->
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

fun Date.format(): String =
   SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(this)

fun String.parseDate() =
   SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this).format()