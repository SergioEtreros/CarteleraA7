package com.senkou.carteleraa7.ui.common

import com.senkou.carteleraa7.domain.model.Sala
import com.senkou.carteleraa7.domain.model.Sesion
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
         this.removeFirst()
      }
   }
}

fun Sesion.crearDetalles(): ArrayList<String> {

   val textoFicha: ArrayList<String> = ArrayList()
   textoFicha.add("")
   textoFicha.add("Título original: ${this.tituloOriginal}")
   textoFicha.add("Duración: ${this.duracion}")
//        textoFicha.add("Director: ${this.director}")
   textoFicha.add("Género: ${this.nombreGenero}")
   textoFicha.add("Calificación: ${this.nombreCalificacion}")
   textoFicha.add("Reparto: ${this.interpretes}")
   textoFicha.add("Fecha de estreno: ${this.fechaEstrenoSpanish}")
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