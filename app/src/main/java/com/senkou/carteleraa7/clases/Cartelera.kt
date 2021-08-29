package com.senkou.carteleraa7.clases

import android.view.View
import android.widget.ProgressBar
import com.google.gson.Gson
import com.senkou.carteleraa7.activities.PeliculaListActivity
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import java.net.URL

abstract class Cartelera (private val activity: PeliculaListActivity) {

    var peliculas: MutableList<Peli> = ArrayList()

    abstract fun rellenarSpinnerDia()
    abstract fun notificarInsercion(posicion: Int)

    fun iniciarDatosCartelera (adapter: PeliculaItemRecyclerViewAdapter, progreso: ProgressBar) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                Jsoup.connect("https://artesiete.es/Cine/13/Artesiete-Segovia").get().run {
                    val cuerpo = this.getElementById("wrapper")
                    val utils = StringEscapeUtils.unescapeHtml4(cuerpo.html())
                    val json = "{\"Cartelera\":" + utils.substring(
                        utils.indexOf(":[") + 1,
                        utils.lastIndexOf("rootUrl") - 2
                    ) + "}"

                    Gson().fromJson(json,Json4KotlinBase::class.java).pelis.toMutableList().forEach {peli->

                        val jsonPases= URL("https://www.artesiete.es/Pelicula/${peli.codigo}/13/").readText()
                        val pasesPelis = Gson().fromJson(jsonPases, PasesPelis::class.java)

                        pasesPelis.Programacion.forEach { programa->
                            programa.Pelis.forEach {sesion->
                                sesion.Pases.forEach {pase->
                                    peli.sesiones.forEach {
                                        if (it.iD_Sesion == pase.ID_Pase.toInt()) {
                                            it.fecha = programa.FechaEfectiva
                                        }
                                    }
                                }
                            }
                        }
                        peliculas.add(peli)
                    }

                    activity.runOnUiThread {
                        progreso.visibility = View.GONE
                        adapter.notifyItemRangeInserted(0, peliculas.size)
                    }
                    rellenarSpinnerDia()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun toString(): String {
        return "Cartelera(peliculas=$peliculas)"
    }
}