package com.senkou.carteleraa7.repository

import com.google.gson.Gson
import com.senkou.carteleraa7.data.IRepository
import com.senkou.carteleraa7.data.model.Json4KotlinBase
import com.senkou.carteleraa7.data.model.PasesPelis
import org.apache.commons.text.StringEscapeUtils
import java.net.HttpURLConnection
import java.net.URL

class RepoWeb: IRepository{

    override fun obtenerCartelera(): Json4KotlinBase? {
        var response:Json4KotlinBase? = null
        try {
            val url = URL("https://artesiete.es/Cine/13/Artesiete-Segovia")
            val urlConnection = url.openConnection() as HttpURLConnection

            var json: String
            try {

                val html = urlConnection.inputStream.bufferedReader().readText()

                json = "{\"Cartelera\":" + html.substring(
                    html.indexOf("Peliculas&quot;:[") + 16,
                    html.lastIndexOf("rootUrl") - 7
                ) + ","

                json += "\"Proximamente\":" + html.substring(
                    html.indexOf("Proximamentes&quot;:[") + 20,
                    html.indexOf("],&quot;Elijapase&quot") +1
                ) + "}"

            } finally {
                urlConnection.disconnect()
            }
            json = StringEscapeUtils.unescapeHtml4(json)
//            json = StringEscapeUtils.unescapeEcmaScript(json)
//            json = String(json.toByteArray(Charsets.ISO_8859_1))
//            jsonProx = StringEscapeUtils.unescapeHtml4(jsonProx)
//            jsonProx = StringEscapeUtils.unescapeEcmaScript(jsonProx)
//            jsonProx = String(jsonProx.toByteArray(Charsets.ISO_8859_1))
            response = Gson().fromJson(
                json,
                Json4KotlinBase::class.java
            )

            response?.pelis?.toMutableList()?.forEach { peli ->

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
            }

            response?.proximosEstrenos?.forEach {estreno->
                estreno.cartel = "https://artesiete.es/Posters/${estreno.cartel}"
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}