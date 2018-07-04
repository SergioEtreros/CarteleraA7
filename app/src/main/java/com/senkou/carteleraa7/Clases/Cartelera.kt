package com.senkou.carteleraa7.Clases

import android.content.Context
import android.widget.Toast
import com.senkou.carteleraa7.adapters.PeliculaItemRecyclerViewAdapter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import java.util.*

class Cartelera(context: Context) {

    var peliculas: MutableList<Pelicula> = ArrayList()
    var ventaAnticipada: MutableList<Pelicula> = ArrayList()
    var proximamente: MutableList<Pelicula> = ArrayList()
    val context = context

    fun iniciarDatosCartelera (adapter : PeliculaItemRecyclerViewAdapter) {
            doAsync {
            Jsoup.connect("http://www.artesiete.es/principal/index/0/020/Artesiete%20Segovia").get().run {
                this.getElementsByClass("sz_image_003_wrapper").forEachIndexed { index, grupo ->
                    grupo.getElementsByClass("sz_image_003_trig_wrap").forEachIndexed { indice, it ->
                        val objetoImg = it.select("img")
                        val urlImg = objetoImg.attr("src")
                        val objetoFicha = it.getElementsByAttributeValueContaining("class","fancybox.ajax")
                        val urlFicha = objetoFicha.attr("href")
                        val idPelicula = urlFicha.substringAfterLast("/")
                        val objetoTrailer = it.getElementsByAttributeValueContaining("href","youtube")
                        val urlTrailer = objetoTrailer.attr("href")
                        if (index == 0 || index == 1) {
                            val enlace = it.getElementsByClass("tamletra2")[1]
                            val tituloPelicula = enlace.text()

                            if (index == 0){
                                peliculas.add(Pelicula(tituloPelicula, urlImg, urlFicha, urlTrailer, idPelicula, false))
                                uiThread {
                                    adapter.notifyDataSetChanged()
                                }
                                if (indice.equals(it.childNodeSize()-1))
                                {
                                    uiThread {
                                        Toast.makeText(context, "Carga Completa", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                peliculas.last().obtenerDatosFicha()
                            }
                            else if (index == 1){

                                ventaAnticipada.add(Pelicula(tituloPelicula, urlImg, urlFicha, urlTrailer, idPelicula, false))
                                uiThread {
                                    adapter.notifyDataSetChanged()
                                }
                                ventaAnticipada.last().obtenerDatosFicha()
                            }
                        }else{
                            val tituloPelicula = it.getElementsByAttributeValue("href", "#")[1].text()
                            proximamente.add(Pelicula(tituloPelicula, urlImg, urlFicha, urlTrailer, idPelicula, true))
                            uiThread {
                                adapter.notifyDataSetChanged()
                            }
                            proximamente.last().obtenerDatosFicha()
                        }
                    }

                }
            }
        }
    }

    override fun toString(): String {
        return "Cartelera(peliculas=$peliculas)"
    }
}