package com.senkou.carteleraa7.Clases

import org.jetbrains.anko.collections.forEachReversedWithIndex
import org.jetbrains.anko.collections.forEachWithIndex
import org.jetbrains.anko.doAsyncResult
import org.jsoup.Jsoup
import java.util.*
import kotlin.collections.ArrayList

class Pelicula (
        val tituloPelicula: String,
        val urlCartel : String,
        private val urlFicha : String,
        val urlTrailer : String,
        private val idPelicula : Int,
        private val sesiones : PasesVersiones
){
    private var urlHorarios : String = ""
    var textoFicha:ArrayList<String> = ArrayList<String>()
    val codigoCine = "22"
    var textoSesionesHoy: String = ""

    class Salas (val sala:String, var horas:String){}

    init {
//        this.textoSesionesHoy = sesiones.version+": "

        var sesions = ArrayList<Salas>()
        sesiones.pases.forEachWithIndex { i, pases ->

//            if (i == 0)
//                sesions.add(Salas(pases.sala, pases.hora.substring(0, pases.hora.lastIndexOf(":"))))
//            else{
                var obj = sesions.find { s -> s.sala == pases.sala}
                if ( obj != null ){
                    obj.horas += " - " + pases.hora.substring(0, pases.hora.lastIndexOf(":"))
                }
                else
                    sesions.add(Salas(pases.sala, pases.hora.substring(0, pases.hora.lastIndexOf(":"))))
//            }
        }

        sesions.forEachWithIndex { i, salas ->
            if (i == (sesions.size -1) )
                this.textoSesionesHoy += salas.sala.replace("0", "") + " : " + salas.horas
            else
                this.textoSesionesHoy += salas.sala.replace("0", "") + " : " + salas.horas + "\n"
        }

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

        if (idPelicula != 0 && fecha.isNotEmpty()){

            urlHorarios = "https://artesiete.es/PeliculaCine/$idPelicula/$codigoCine"
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

