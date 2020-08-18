package com.senkou.carteleraa7.clases

import android.view.View
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import org.apache.commons.text.StringEscapeUtils
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.util.*

class Cartelera {

    var peliculas: MutableList<Peli> = ArrayList()

    fun iniciarDatosCartelera (adapter: PeliculaItemRecyclerViewAdapter, progreso: ProgressBar, spDia: Spinner) {
        doAsync {

            Jsoup.connect("https://prc.artesiete.es/Cine/22/Artesiete-segovia").get().run {
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

            // rellenamos el spinner de los dias
            if (spDia.adapter.count <= 1){
                val fechasSpinner = arrayListOf<String>()
                peliculas.forEach { peli: Peli ->  peli.sesiones.forEach{pases: Pases ->
                    if (!fechasSpinner.contains(pases.fecha)){
                        fechasSpinner.add(pases.fecha)
                    }
                }}
                fechasSpinner.sort()

                fechasSpinner[0] = spDia.context.getString(R.string.dia_hoy)

                val adapterSP = ArrayAdapter(spDia.context,
                        android.R.layout.simple_spinner_item, fechasSpinner)
                spDia.adapter = adapterSP
                adapterSP.notifyDataSetChanged()
            }
        }
    }

    override fun toString(): String {
        return "Cartelera(peliculas=$peliculas)"
    }
}