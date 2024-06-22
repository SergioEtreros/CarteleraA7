package com.senkou.framework.remote

import com.google.gson.Gson
import com.senkou.data.RemoteDataSource
import com.senkou.domain.model.Cartelera
import com.senkou.framework.remote.model.InfoCine
import com.senkou.framework.remote.model.Pelicula
import com.senkou.framework.remote.model.Sesion
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

         var json: String
         try {

            val html = urlConnection.inputStream.bufferedReader().readText()

            json = "{\"Cartelera\":" + html.substring(
               html.indexOf(":onlytitlesinfo='[") + 17,
               html.lastIndexOf("}]' :fullsessionsinfo") + 2
            ) + ","

//            json += "\"Sesiones\":" + html.substring(
//               html.indexOf(":fullsessionsinfo='[") + 19,
//               html.lastIndexOf(";}]") + 3
//            ) + ","

            json += obtenerEstrenos(html)

            json += "}"

         } finally {
            urlConnection.disconnect()
         }
         json = StringEscapeUtils.unescapeHtml4(json)

         val response = Gson().fromJson(
            json.replace("\\/", "/"),
            InfoCine::class.java
         )

         return response.toDomain()

      } catch (e: Exception) {
         e.printStackTrace()
         return Cartelera(emptyList(), emptyList())
      }
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
               val titulo = estreno.child(0).child(1).attr("alt").toString()

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

   override suspend fun getSsiones(): List<SesionDomain> {

      try {
         val url = URL("https://artesiete.es/Cine/13/Artesiete-segovia")
         val urlConnection = withContext(Dispatchers.IO) {
            url.openConnection()
         } as HttpURLConnection

         var json: String
         try {

            val html = urlConnection.inputStream.bufferedReader().readText()

            json = "{\"Sesiones\":" + html.substring(
               html.indexOf(":fullsessionsinfo='[") + 19,
               html.lastIndexOf(";}]") + 3
            ) + ","

            json += obtenerEstrenos(html)

            json += "}"

         } finally {
            urlConnection.disconnect()
         }
         json = StringEscapeUtils.unescapeHtml4(json)

         val response = Gson().fromJson(
            json.replace("\\/", "/"),
            InfoCine::class.java
         )

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
   iDEspectaculo = iDEspectaculo,
   titulo = titulo

)

private fun InfoCine.toDomain() = Cartelera(
   peliculas = this.pelis.map { it.toDomain() },
   proximosEstrenos = this.proximosEstrenos.map { it.toDomain() }
)

private fun String.getUrlCartel(): String =
   this.takeIf { it.startsWith("https") } ?: (RUTA_CARTELES + this)