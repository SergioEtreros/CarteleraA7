package com.senkou.framework.remote.arte7

import com.google.gson.Gson
import com.senkou.data.RemoteDataSource
import com.senkou.domain.model.Cartelera
import com.senkou.framework.remote.arte7.model.InfoCine
import com.senkou.framework.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class WebMovieDatasource @Inject constructor() : RemoteDataSource {
   override suspend fun getCartelera(): Cartelera = withContext(Dispatchers.IO) {
      try {
         val url = URL("https://artesiete.es/Cine/13/Artesiete-segovia")
         val urlConnection = url.openConnection() as HttpURLConnection

         val rawJson: String
         val estrenos: String
         val sesiones: String
         try {
            val html = urlConnection.inputStream.bufferedReader().readText()

            rawJson = html.substringAfter(":onlytitlesinfo='")
               .substringBefore("}]") + "}]"

            estrenos = obtenerEstrenos(html)

            sesiones = html.substringAfter(":fullsessionsinfo='")
               .substringBefore("}]") + "}]"
         } finally {
            urlConnection.disconnect()
         }

         var json = """
            { 
               "Cartelera" : $rawJson, 
               "Proximamente" : $estrenos,
               "Sesiones" : $sesiones
            }
            """

         json = StringEscapeUtils.unescapeHtml4(json).replace("\\/", "/")

         val response = Gson().fromJson(json,InfoCine::class.java)

         response.toDomain()

      } catch (e: Exception) {
         e.printStackTrace()
         Cartelera(emptyList(), emptyList())
      }
   }

   private fun obtenerEstrenos(html: String): String {
      var json = "[\n"

      Jsoup.parse(html).apply {
         this.getElementsByClass("swiper mySwiperNext mb-5").first()
            ?.getElementsByClass("swiper-slide")?.forEachIndexed { index, estreno ->

               val parser = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
               val fechaEstreno =
                  parser.parse(estreno.child(0).child(0).textNodes()[0].toString().trim())
               var fecha = ""

               val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

               fechaEstreno?.let { fecha = formatter.format(fechaEstreno) }

               val cartel = estreno.child(0).child(1).attr("src")
               val titulo = estreno.child(0).child(1).attr("alt")

               if (json.length > 20) {
                  json += ",\n"
               }

               json += """
               {    
                  "Cartel" : "$cartel",
                  "FechaEstreno" : "$fecha",
                  "ID_Espectaculo" : $index,
                  "Titulo" : "$titulo",
                  "TituloOriginal" : "$titulo"
               }
               """
            }
      }
      json += "\n]"

      return json
   }
}
