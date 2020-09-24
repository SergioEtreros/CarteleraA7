/* 
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
package com.senkou.carteleraa7.clases

import com.google.gson.annotations.SerializedName
import org.jetbrains.anko.collections.forEachWithIndex
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class Peli (

		@SerializedName("Codigo") val codigo : Int,
		@SerializedName("Distribuidora") val distribuidora : String,
		@SerializedName("Genero") val genero : String,
		@SerializedName("Director") val director : String,
		@SerializedName("Actores") val actores : String,
		@SerializedName("Nacionalidad") val nacionalidad : String,
		@SerializedName("Titulo") val titulo : String,
		@SerializedName("TituloOriginal") val tituloOriginal : String,
		@SerializedName("Duracion") val duracion : Int,
		@SerializedName("FechaEstreno") val fechaEstreno : String,
		@SerializedName("Calificacion") val calificacion : String,
		@SerializedName("Sinopsis") val sinopsis : String,
		@SerializedName("IdiomaOriginal") val idiomaOriginal : String,
		@SerializedName("UrlImagen") val urlImagen : String,
		@SerializedName("Video") val video : String,
		@SerializedName("Audio") val audio : String,
		@SerializedName("Idioma") val idioma : String,
		@SerializedName("MostrarEstreno") val mostrarEstreno : Int,
		@SerializedName("Sesiones") val sesiones : List<Pases>
){
	var textoFicha:ArrayList<String> = ArrayList()

	class Salas (val sala:String, var horas:String)

	fun crearTextoSesiones(dia:String):String{

		val sesions = ArrayList<Salas>()
		var textoSesionesHoy = ""

		val diaSesiones = if (dia.isNotEmpty())dia else{
			val formater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
			val cal = Calendar.getInstance()
			formater.format(cal.time)}

		if (!sesiones.isNullOrEmpty()){
			sesiones.forEachWithIndex { _, pases ->

//				println("Fechas: -$diaSesiones- -${pases.fecha}-")

				if (pases.fecha == diaSesiones) {

					val obj = sesions.find { s -> s.sala == pases.sala }
					if (obj != null) {
						obj.horas += " - " + pases.hora.substring(0, pases.hora.lastIndexOf(":"))
					} else {
						sesions.add(Salas(pases.sala, pases.hora.substring(0, pases.hora.lastIndexOf(":"))))
					}
				}
			}
		}

		sesions.forEachWithIndex { i, salas ->
			textoSesionesHoy += if (i == (sesions.size -1) )
					salas.sala.replace("0", "") + " : " + salas.horas
				else
					salas.sala.replace("0", "") + " : " + salas.horas + "\n"
		}

		crearDetalles()
		return textoSesionesHoy
	}

	private fun crearDetalles(){

		if (textoFicha.isNullOrEmpty()){
			textoFicha = ArrayList()
			textoFicha.add("")
			textoFicha.add("Título original: $tituloOriginal")
            textoFicha.add("Duración: $duracion")
            textoFicha.add("Director: $director")
            textoFicha.add("Género: $genero")
            textoFicha.add("Calificación: $calificacion")
			textoFicha.add("Reparto: $actores")
            textoFicha.add("Fecha de estreno: $fechaEstreno")
            textoFicha.add(sinopsis)
		}
	}
}