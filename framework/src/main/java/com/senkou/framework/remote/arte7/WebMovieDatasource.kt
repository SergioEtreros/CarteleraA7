package com.senkou.framework.remote.arte7

import com.google.gson.Gson
import com.senkou.data.RemoteDataSource
import com.senkou.domain.model.Cartelera
import com.senkou.domain.model.Sesion
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

class WebMovieDatasource : RemoteDataSource {
   override suspend fun getCartelera(): Cartelera = withContext(Dispatchers.IO) {
      try {
         val url = URL("https://artesiete.es/Cine/13/Artesiete-segovia")
         val urlConnection = url.openConnection() as HttpURLConnection

         val rawJson: String
         val estrenos: String
         try {

            val html = urlConnection.inputStream.bufferedReader().readText()

            rawJson = html.substringAfter(":onlytitlesinfo='").substringBefore("}]") + "}]"

            estrenos = obtenerEstrenos(html)
         } finally {
            urlConnection.disconnect()
         }

         var json = """
            { 
               "Cartelera" : $rawJson, 
               "Proximamente" : $estrenos
            }
            """

         json = StringEscapeUtils.unescapeHtml4(json).replace("\\/", "/")

         val response = Gson().fromJson(
            json,
            InfoCine::class.java
         )

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

               val cartel = estreno.child(0).child(1).attr("src").toString()
               val titulo = estreno.child(0).child(1).attr("alt").toString()

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

   override suspend fun getSesiones(idEspectaculo: Int): List<Sesion> = withContext(Dispatchers.IO) {

      try {
         val url = URL("https://artesiete.es/Cine/13/Artesiete-segovia")
         val urlConnection = url.openConnection() as HttpURLConnection

         val html: String?
         try {
            html = urlConnection.inputStream.bufferedReader().readText()
         } finally {
            urlConnection.disconnect()
         }

         val rawJson = html?.substringAfter(":fullsessionsinfo='")?.substringBefore("}]") + "}]"

         val json = StringEscapeUtils.unescapeHtml4("{\"Sesiones\": $rawJson}").replace("\\/", "/")

         val response = Gson().fromJson(json, InfoCine::class.java)

         response.sesiones
            .map { it.toDomain() }
            .filter { it.idEspectaculo == idEspectaculo }

      } catch (e: Exception) {
         e.printStackTrace()
         emptyList()
      }
   }
}
