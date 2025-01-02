package com.senkou.framework.remote.arte7

import com.google.gson.Gson
import com.senkou.data.RemoteDataSource
import com.senkou.domain.model.Cartelera
import com.senkou.framework.remote.arte7.model.InfoCine
import com.senkou.framework.remote.arte7.model.Pelicula
import com.senkou.framework.remote.arte7.model.Sesion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import com.senkou.domain.model.Pelicula as PeliculaDomain
import com.senkou.domain.model.Sesion as SesionDomain

const val RUTA_CARTELES = "https://artesiete.es/Posters/"

class WebMovieDatasource : RemoteDataSource {
   override suspend fun getCartelera(): Cartelera {
      try {
         val url = URL("https://artesiete.es/Cine/13/Artesiete-segovia")
         val urlConnection = withContext(Dispatchers.IO) {
            url.openConnection()
         } as HttpURLConnection

         val rawJson: String
         val estrenos: String
         try {

            val html = urlConnection.inputStream.bufferedReader().readText()

            rawJson = html.substring(
               html.indexOf(":onlytitlesinfo='[") + 17,
               html.lastIndexOf("}]' :fullsessionsinfo") + 2
            )

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

         return response.toDomain()

      } catch (e: Exception) {
         e.printStackTrace()
         return Cartelera(emptyList(), emptyList())
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

   override suspend fun getSsiones(): List<SesionDomain> {

      try {
         val url = URL("https://artesiete.es/Cine/13/Artesiete-segovia")
         val urlConnection = withContext(Dispatchers.IO) {
            url.openConnection()
         } as HttpURLConnection

         val html: String?
         try {
            html = urlConnection.inputStream.bufferedReader().readText()
         } finally {
            urlConnection.disconnect()
         }

         val rawJson = html?.substring(
            html.indexOf(":fullsessionsinfo='[") + 19,
            html.lastIndexOf(";}]") + 3
         )

         val json = StringEscapeUtils.unescapeHtml4("{\"Sesiones\": $rawJson}").replace("\\/", "/")

         val response = Gson().fromJson(json, InfoCine::class.java)

         return response.sesiones.map { it.toDomain() }

      } catch (e: Exception) {
         e.printStackTrace()
         return emptyList()
      }
   }
}

fun InfoCine?.orElse(alternateObject: InfoCine): InfoCine = alternateObject

private fun Sesion.toDomain() = SesionDomain(
   duracion = duracion,
   fechaEstrenoSpanish = fechaEstrenoSpanish,
   hora = hora,
   iDEspectaculo = iDEspectaculo,
   iDPase = iDPase,
   iDSala = iDSala,
   nombreSala = nombreSala,
   nombreFormato = nombreFormato,
   interpretes = interpretes,
   nombreCalificacion = nombreCalificacion,
   nombreGenero = nombreGenero,
   sinopsis = sinopsis,
   titulo = titulo,
   tituloOriginal = tituloOriginal,
   video = video,
   cartel = cartel.getUrlCartel(),
   diacompleto = diacompleto,
)

private fun Pelicula.toDomain() = PeliculaDomain(
   cartel = cartel.getUrlCartel(),
   fechaEstreno = fechaEstreno,
   idEspectaculo = idEspectaculo,
   titulo = titulo,
   tituloOriginal = tituloOriginal
)

private fun InfoCine.toDomain() = Cartelera(
   peliculas = this.pelis.map { it.toDomain() },
   proximosEstrenos = this.proximosEstrenos?.let { estrenos -> estrenos.map { it.toDomain() } }
      ?: emptyList()
)

private fun String.getUrlCartel(): String =
   this.takeIf { it.startsWith("https") } ?: (RUTA_CARTELES + this)