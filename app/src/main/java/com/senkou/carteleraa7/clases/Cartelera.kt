package com.senkou.carteleraa7.clases

import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import org.apache.commons.text.StringEscapeUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.util.*

class Cartelera {

    var peliculas: MutableList<Peli> = ArrayList()

    fun iniciarDatosCartelera (adapter : PeliculaItemRecyclerViewAdapter, progreso: ProgressBar) {
            doAsync {

            Jsoup.connect("https://artesiete.es/CineL/22/Artesiete-Segovia").get().run {
                val cuerpo = this.getElementById("wrapper")
                val utils = StringEscapeUtils.unescapeHtml4(cuerpo.html())
                val json = "{\"Cartelera\":" + utils.substring(utils.indexOf(":[")+1, utils.lastIndexOf("rootUrl")-2)+"}"

                peliculas.addAll(Gson().fromJson(json, Json4KotlinBase::class.java).pelis.toMutableList())
                //peliculas.removeAll{ x -> x.fechasSesiones[0].pasesVersiones[0].pases[0].enVentaAnticipada == 1}
                uiThread {
                    progreso.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun toString(): String {
        return "Cartelera(peliculas=$peliculas)"
    }
}