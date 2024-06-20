package com.senkou.wear.data.model


import com.google.gson.annotations.SerializedName
import org.apache.commons.text.StringEscapeUtils

data class ProximoEstreno(
   @SerializedName("AbreviaturaCalificacion")
   val abreviaturaCalificacion: String,
   @SerializedName("Ano")
   val ano: String,
   @SerializedName("ApellidosDirectorPrincipal")
   val apellidosDirectorPrincipal: String,
   @SerializedName("Calificacion")
   val calificacion: String,
   @SerializedName("Cartel")
   var cartel: String,
   @SerializedName("Cartel2")
   val cartel2: String,
   @SerializedName("Cartel3")
   val cartel3: String,
   @SerializedName("CodigoICAADistribuidora")
   val codigoICAADistribuidora: String,
   @SerializedName("CodigoPaisPrincipal")
   val codigoPaisPrincipal: String,
   @SerializedName("Comunitaria")
   val comunitaria: String,
   @SerializedName("Director")
   val director: String,
   @SerializedName("Duracion")
   val duracion: String,
   @SerializedName("FEst")
   val fEst: String,
   @SerializedName("FechaActualizacion")
   val fechaActualizacion: String,
   @SerializedName("FechaEstreno")
   val fechaEstreno: String,
   @SerializedName("Genero")
   val genero: String,
   @SerializedName("ID_Distribuidora")
   val iDDistribuidora: String,
   @SerializedName("ID_Espectaculo")
   val iDEspectaculo: String,
   @SerializedName("ID_Pelicula")
   val iDPelicula: String,
   @SerializedName("ID_ProxEstreno")
   val iDProxEstreno: String,
   @SerializedName("IconoCalificacion")
   val iconoCalificacion: String,
   @SerializedName("IdiomaOriginal")
   val idiomaOriginal: String,
   @SerializedName("Interpretes")
   val interpretes: String,
   @SerializedName("Largometraje")
   val largometraje: String,
   @SerializedName("Lenguaje")
   val lenguaje: String,
   @SerializedName("LetraIdiomaOriginal")
   val letraIdiomaOriginal: String,
   @SerializedName("ModificadoPor")
   val modificadoPor: String,
   @SerializedName("NCalificacion")
   val nCalificacion: String,
   @SerializedName("NExpediente")
   val nExpediente: String,
   @SerializedName("NRollos")
   val nRollos: String,
   @SerializedName("Nacionalidad")
   val nacionalidad: String,
   @SerializedName("NombreCalificacion")
   val nombreCalificacion: String,
   @SerializedName("NombreDirectorPrincipal")
   val nombreDirectorPrincipal: String,
   @SerializedName("NombreDistribuidora")
   val nombreDistribuidora: String,
   @SerializedName("NombreGenero")
   val nombreGenero: String,
   @SerializedName("PDF")
   val pDF: String,
   @SerializedName("Pelicula")
   val pelicula: String,
   @SerializedName("PeliculaX")
   val peliculaX: String,
   @SerializedName("Siglas")
   val siglas: String,
   @SerializedName("Sinopsis")
   val sinopsis: String,
   @SerializedName("SinopsisAlternativa")
   val sinopsisAlternativa: String,
   @SerializedName("Sinopsisalternativa2")
   val sinopsisalternativa2: String,
   @SerializedName("TiempoLimpieza")
   val tiempoLimpieza: String,
   @SerializedName("TiempoTrailer")
   val tiempoTrailer: String,
   @SerializedName("TipoEventoCodigo")
   val tipoEventoCodigo: String,
   @SerializedName("TipoEventoDescripcion")
   val tipoEventoDescripcion: String,
   @SerializedName("Titulo")
   val titulo: String,
   @SerializedName("TituloOriginal")
   val tituloOriginal: String,
   @SerializedName("Video")
   val video: String,
   @SerializedName("VideoSecundario")
   val videoSecundario: String,
   @SerializedName("WhatsCine")
   val whatsCine: String
) {
   fun crearDetalles(): ArrayList<String> {

      val textoFicha: ArrayList<String> = ArrayList()
      textoFicha.add("")
      textoFicha.add("Título original: ${decodeString(tituloOriginal)}")
      textoFicha.add("Duración: ${decodeString(duracion)}")
      textoFicha.add("Director: ${decodeString(director)}")
      textoFicha.add("Género: ${decodeString(genero)}")
      textoFicha.add("Calificación: ${decodeString(calificacion)}")
      textoFicha.add("Reparto: ${decodeString(interpretes)}")
      textoFicha.add("Fecha de estreno: ${decodeString(fechaEstreno)}")
      textoFicha.add(decodeString(sinopsis))

      return textoFicha
   }

   private fun decodeString(s: String): String {
      var cadena = s
      cadena = StringEscapeUtils.unescapeEcmaScript(cadena)
      cadena = String(cadena.toByteArray(Charsets.ISO_8859_1))
      return cadena
   }
}