package com.senkou.carteleraa7.Clases

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.util.*
import kotlin.collections.ArrayList

class Pelicula (
        val tituloPelicula: String,
        val urlCartel : String,
        private val urlFicha : String,
        val urlTrailer : String,
        private val idPelicula : String,
        private val esProximoEstreno : Boolean
){
    private var urlHorarios : String = ""
    var textoFicha:ArrayList<String> = ArrayList<String>()
    val codigoCine = "020"
    var textoSesionesHoy: String = ""

    init {
        if (!esProximoEstreno)
            this.textoSesionesHoy = obtenerHorasSesionesHoy()
        else
            this.textoSesionesHoy = "Sin sesiones"
    }

    private fun obtenerHorasSesionesHoy(): String {
        val fecha = Calendar.getInstance()
        val dia:String = String.format("%02d",fecha.get(Calendar.DAY_OF_MONTH))
        val mes:String = String.format("%02d",fecha.get(Calendar.MONTH)+1)
        val anio:String = fecha.get(Calendar.YEAR).toString()
        val fechaFormateada = "$dia-$mes-$anio"

        return obtenerHorasSesiones(fechaFormateada)
    }

    fun obtenerHorasSesiones(fecha : String): String {
        var sesiones = ""

        if (idPelicula.isNotEmpty() && fecha.isNotEmpty()){

            urlHorarios = "http://www.artesiete.es/Pelicula/HorariosDia/$fecha/$idPelicula/$codigoCine"
            val detallesHoras = Jsoup.connect(urlHorarios).get()
            detallesHoras.run {
                detallesHoras.getElementsByAttributeValueContaining("href", "compraentradas").forEachIndexed { _, element ->
                    sesiones += if (sesiones.isEmpty())
                        "Sesiones: " + element.text()
                    else
                        " - " + element.text()
                }
            }
        }
        return sesiones
    }

    fun obtenerDatosFicha(){
        if (textoFicha.isEmpty()){
            doAsyncResult{
                Jsoup.connect(urlFicha).get().run {
                    this.select("div").forEachIndexed { index, element ->
                        if (index > 1)
                            textoFicha.add(element.text())
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return "Pelicula(tituloPelicula='$tituloPelicula', textoSesionesHoy='$textoSesionesHoy', urlCartel='$urlCartel', urlFicha='$urlFicha', urlTrailer='$urlTrailer', urlHorarios='$urlHorarios', textoFicha='${textoFicha.toString()}')"
    }
}

