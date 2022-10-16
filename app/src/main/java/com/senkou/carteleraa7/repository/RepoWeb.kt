package com.senkou.carteleraa7.repository

import com.google.gson.Gson
import com.senkou.carteleraa7.data.IRepository
import com.senkou.carteleraa7.data.data_clases.Json4KotlinBase
import com.senkou.carteleraa7.data.data_clases.PasesPelis
import com.senkou.carteleraa7.data.data_clases.Peli
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import java.net.URL

class RepoWeb: IRepository{

    override fun obtenerCartelera(): MutableList<Peli> {
        val pelisAux: MutableList<Peli> = ArrayList()
        try {
            Jsoup.connect("https://artesiete.es/Cine/13/Artesiete-Segovia").get().run {
                val cuerpo = this.getElementById("wrapper")
                val utils = StringEscapeUtils.unescapeHtml4(cuerpo.html())
                val json = "{\"Cartelera\":" + utils.substring(
                    utils.indexOf(":[") + 1,
                    utils.lastIndexOf("rootUrl") - 2
                ) + "}"

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
                    pelisAux.add(peli)
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return pelisAux
    }
}