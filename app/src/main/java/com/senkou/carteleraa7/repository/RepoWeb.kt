package com.senkou.carteleraa7.repository

import com.google.gson.Gson
import com.senkou.carteleraa7.data.IRepository
import com.senkou.carteleraa7.data.model.Json4KotlinBase
import com.senkou.carteleraa7.data.model.PasesPelis
import com.senkou.carteleraa7.data.model.Peli
import org.apache.commons.text.StringEscapeUtils
import java.net.HttpURLConnection
import java.net.URL

class RepoWeb: IRepository{

    override fun obtenerCartelera(): MutableList<Peli> {
        val pelisAux: MutableList<Peli> = ArrayList()
        try {
                val url = URL("https://artesiete.es/Cine/13/Artesiete-Segovia")
                val urlConnection = url.openConnection() as HttpURLConnection

                var json = ""
                try {
                    val utils = urlConnection.inputStream.bufferedReader().readText()
                    json = "{\"Cartelera\":" + utils.substring(
                        utils.indexOf("Peliculas&quot;:[") + 16,
                        utils.lastIndexOf("rootUrl") - 7
                    ) + "}"
                } finally {
                    urlConnection.disconnect()
                }
                json = StringEscapeUtils.unescapeHtml4(json)
                Gson().fromJson(
                    json,
                    Json4KotlinBase::class.java
                ).pelis.toMutableList().forEach { peli ->

                    val jsonPases =
                        URL("https://www.artesiete.es/Pelicula/${peli.codigo}/13/").readText()
                    val pasesPelis = Gson().fromJson(jsonPases, PasesPelis::class.java)

                    pasesPelis.Programacion.forEach { programa ->
                        programa.Pelis.forEach { sesion ->
                            sesion.Pases.forEach { pase ->
                                peli.sesiones.forEach {
                                    if (it.iD_Sesion == pase.ID_Pase.toInt()) {
                                        it.fecha = programa.FechaEfectiva
                                    }
                                }
                            }
                        }
                    }
                    peli.urlImagen = "https://artesiete.es${peli.urlImagen}"
                    pelisAux.add(peli)
                }
//            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return pelisAux
    }
}