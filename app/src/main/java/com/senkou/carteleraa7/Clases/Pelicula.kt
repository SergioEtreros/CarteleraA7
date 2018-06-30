package com.senkou.carteleraa7.Clases

import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup

class Pelicula (
        val tituloPelicula: String,
        val urlCartel : String,
        private val urlFicha : String,
        val urlTrailer : String,
        val urlHorarios : String
){
    var textoFicha:String = ""

    fun obtenerDatosFicha(){
        if (textoFicha.isEmpty()){
            doAsync {
                Jsoup.connect(urlFicha).get().run {
                    this.select("div").forEach {
                        textoFicha += it.text()
                    }
                }
            }
        }
    }

    override fun toString(): String {
        return "Pelicula(tituloPelicula='$tituloPelicula', urlCartel='$urlCartel', urlFicha='$urlFicha', urlTrailer='$urlTrailer', urlHorarios='$urlHorarios', textoFicha='$textoFicha')"
    }
}