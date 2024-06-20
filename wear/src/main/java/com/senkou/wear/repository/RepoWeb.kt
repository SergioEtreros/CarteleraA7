package com.senkou.wear.repository

import com.google.gson.Gson
import com.senkou.wear.data.IRepository
import com.senkou.wear.data.model.InfoCine
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Locale

class RepoWeb : IRepository {

   override fun obtenerCartelera(): InfoCine? {
      var response: InfoCine? = null
      try {
         val url = URL("https://artesiete.es/Cine/13/Artesiete-segovia")
         val urlConnection = url.openConnection() as HttpURLConnection

         var json: String
         try {

            val html = urlConnection.inputStream.bufferedReader().readText()

            json = "{\"Cartelera\":" + html.substring(
               html.indexOf(":onlytitlesinfo='[") + 17,
               html.lastIndexOf("}]' :fullsessionsinfo") + 2
            ) + ","

            json += "\"Sesiones\":" + html.substring(
               html.indexOf(":fullsessionsinfo='[") + 19,
               html.lastIndexOf(";}]") + 3
            ) + ","

            json += obtenerEstrenos(html)

            json += "}"

         } finally {
            urlConnection.disconnect()
         }
         json = StringEscapeUtils.unescapeHtml4(json)
//            json = StringEscapeUtils.unescapeEcmaScript(json)
//            json = String(json.toByteArray(Charsets.ISO_8859_1))
//            jsonProx = StringEscapeUtils.unescapeHtml4(jsonProx)
//            jsonProx = StringEscapeUtils.unescapeEcmaScript(jsonProx)
//            jsonProx = String(jsonProx.toByteArray(Charsets.ISO_8859_1))
         response = Gson().fromJson(
            json.replace("\\/", "/"),
            InfoCine::class.java
         )

//            response?.pelis?.toMutableList()?.forEach { peli ->
//
//                val jsonPases =
//                    URL("https://www.artesiete.es/Pelicula/${peli.iDEspectaculo}/13/").readText()
//                val pasesPelis = Gson().fromJson(jsonPases, PasesPelis::class.java)
//
//                pasesPelis.Programacion.forEach { programa ->
//                    programa.Pelis.forEach { sesion ->
//                        sesion.Pases.forEach { pase ->
//                            peli.sesiones.forEach {
//                                if (it.iD_Sesion == pase.ID_Pase.toInt()) {
//                                    it.fecha = programa.FechaEfectiva
//                                }
//                            }
//                        }
//                    }
//                }
//                peli.urlImagen = "https://artesiete.es${peli.urlImagen}"
//            }
//
//            response?.proximosEstrenos?.forEach {estreno->
//                estreno.cartel = "https://artesiete.es/Posters/${estreno.cartel}"
//            }

      } catch (e: Exception) {
         e.printStackTrace()
      }
      return response
   }

   private fun obtenerEstrenos(html: String): String {
      var json = "\"Proximamente\" :"
      Jsoup.parse(html).apply {

         json += "[\n"
         this.getElementsByClass("swiper mySwiperNext mb-5").first()
            ?.getElementsByClass("swiper-slide")?.forEach { estreno ->
            val parser = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
            val fechaEstreno =
               parser.parse(estreno.child(0).child(0).textNodes()[0].toString().trim())
            var fecha = ""

            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            fechaEstreno?.let { fecha = formatter.format(fechaEstreno) }

            val cartel = estreno.child(0).child(1).attr("src").toString()
            val titulo = estreno.child(1).child(0).textNodes()[0].toString()

            if (json.length > 20) {
               json += ",\n"
            }

            json += "  {\n"
            json += "    \"Cartel\" : \"$cartel\",\n"
            json += "    \"FechaEstreno\" : \"$fecha\",\n"
            json += "    \"ID_Espectaculo\" : 0,\n"
            json += "    \"Titulo\" : \"$titulo\"\n"
            json += "  }"
         }

         json += "\n]"
      }

      return json
   }
}