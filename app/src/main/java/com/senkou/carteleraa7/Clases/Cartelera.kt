package com.senkou.carteleraa7.Clases

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import org.apache.commons.text.StringEscapeUtils
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.util.*

class Cartelera(context: Context) {

    var peliculas: MutableList<Pelicula> = ArrayList()
    var ventaAnticipada: MutableList<Pelicula> = ArrayList()
    var proximamente: MutableList<Pelicula> = ArrayList()
    val context = context

    fun iniciarDatosCartelera (adapter : PeliculaItemRecyclerViewAdapter, progreso: ProgressBar) {
            doAsync {

            Jsoup.connect("https://artesiete.es/CineL/22/Artesiete-Segovia").get().run {
                val cuerpo = this.getElementById("wrapper3")
                var utils = StringEscapeUtils.unescapeHtml4(cuerpo.html())
                val json = "{\"Cartelera\":" + utils.substring(utils.indexOf(":[")+1, utils.lastIndexOf("rootUrl")-2)+"}"

                val cartelera = Gson().fromJson(json, Json4Kotlin_Base::class.java).pelis

                cartelera.forEachWithIndex { i, peli ->
                    if (peli.fechasSesiones[0].pasesVersiones[0].pases[0].enVentaAnticipada == 0)
                        peliculas.add(Pelicula(peli.titulo, peli.urlImagen, "", peli.video, peli.codigo, peli.fechasSesiones[0].pasesVersiones[0]))
                    uiThread {
                        progreso.visibility = View.GONE
//                                    progress.visibility = View.INVISIBLE
                        adapter.notifyDataSetChanged()
                    }
                    if (i.equals(cartelera.size-1))
                    {
                        uiThread {
                            Toast.makeText(context, "Carga Completa", Toast.LENGTH_SHORT).show()
                        }
                    }
//                    peliculas.last().obtenerDatosFicha()
                }
            }
        }
    }

    override fun toString(): String {
        return "Cartelera(peliculas=$peliculas)"
    }
}