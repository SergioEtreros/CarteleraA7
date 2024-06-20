package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.datasources.RemoteDataSource
import com.senkou.carteleraa7.framework.remote.model.InfoCine
import com.senkou.carteleraa7.framework.remote.model.Pelicula
import com.senkou.carteleraa7.framework.remote.model.Sesion
import com.senkou.carteleraa7.domain.model.Cartelera
import com.senkou.carteleraa7.domain.model.Pelicula as PeliculaDomain
import com.senkou.carteleraa7.domain.model.Sesion as SesionDomain

const val RUTA_CARTELES = "https://artesiete.es/Posters/"

class MoviesRepository(
   private val webMovieDatasource: RemoteDataSource
) {
   suspend fun getCartelera() = webMovieDatasource.getCartelera().toDomain()
   suspend fun getSesiones() = webMovieDatasource.getSsiones().sesiones.map { it.toDomain() }
}

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