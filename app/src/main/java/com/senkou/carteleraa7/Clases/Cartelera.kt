package com.senkou.carteleraa7.Clases

import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.util.ArrayList

class Cartelera(){

    var peliculas: MutableList<Pelicula> = ArrayList()
    var ventaAnticipada: MutableList<Pelicula> = ArrayList()
    var proximamente: MutableList<Pelicula> = ArrayList()

    init {
    }

    fun iniciarDatosCartelera (adapter : PeliculaItemRecyclerViewAdapter) {
        doAsync {
            Jsoup.connect("http://www.artesiete.es/principal/index/0/020/Artesiete%20Segovia").get().run {
                this.getElementsByClass("sz_image_003_wrapper").forEachIndexed { index, it ->
                    it.getElementsByClass("sz_image_003_trig_wrap").forEach{
                        val objetoImg = it.select("img")
                        val urlImg = objetoImg.attr("src")
                        val objetoFicha = it.getElementsByAttributeValueContaining("class","fancybox.ajax")
                        val urlFicha = objetoFicha.attr("href")
                        val objetoTrailer = it.getElementsByAttributeValueContaining("href","youtube")
                        val urlTrailer = objetoTrailer.attr("href")
                        if (index == 0 || index == 1) {
                            val enlace = it.getElementsByClass("tamletra2")[1]
                            val tituloPelicula = enlace.text()
                            val urlHorarios = enlace.attr("href")
                            if (index == 0){
                                peliculas.add(Pelicula(tituloPelicula, urlImg, urlFicha, urlTrailer, urlHorarios))
                            }
                            else if (index == 1)
                                ventaAnticipada.add(Pelicula(tituloPelicula, urlImg, urlFicha, urlTrailer, urlHorarios))
                        }else{
                            val tituloPelicula = it.getElementsByAttributeValue("href", "#")[1].text()
                            proximamente.add(Pelicula(tituloPelicula, urlImg, urlFicha, urlTrailer, ""))
                        }
                    }
                }
            }
            uiThread {
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun toString(): String {
        return "Cartelera(peliculas=$peliculas)"
    }
}