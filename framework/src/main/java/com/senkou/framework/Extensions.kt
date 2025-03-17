package com.senkou.framework

import com.senkou.domain.model.Cartelera
import com.senkou.domain.model.Pelicula
import com.senkou.domain.model.Sesion
import com.senkou.framework.local.room.SessionWithBackground
import com.senkou.framework.local.room.entities.MovieEntity
import com.senkou.framework.local.room.entities.SessionEntity
import com.senkou.framework.local.room.entities.UpcomingMoviesEntity
import com.senkou.framework.remote.arte7.model.InfoCine
import com.senkou.framework.remote.arte7.model.PeliculaRemote
import com.senkou.framework.remote.arte7.model.SesionRemote

const val RUTA_CARTELES = "https://artesiete.es/Posters/"

fun MovieEntity.toDomain() = Pelicula(
   cartel = cartel,
   fechaEstreno = releaseDate,
   idEspectaculo = movieId,
   titulo = title,
   tituloOriginal = originalTitle,
   background = background,
)

fun UpcomingMoviesEntity.toDomain() = Pelicula(
   cartel = cartel,
   fechaEstreno = releaseDate,
   idEspectaculo = movieId,
   titulo = title,
   tituloOriginal = originalTitle,
   background = background,
)

fun SessionEntity.toDomain() = Sesion(
   duracion = length,
   fechaEstrenoSpanish = spanishReleaseDate,
   hora = hour,
   idEspectaculo = movieId,
   idPase = sessionId,
   idSala = roomId,
   nombreSala = roomName,
   nombreFormato = format,
   interpretes = interpreters,
   nombreCalificacion = rating,
   nombreGenero = gender,
   sinopsis = synopsis,
   titulo = title,
   tituloOriginal = originalTitle,
   video = video,
   cartel = cartel,
   diacompleto = fullDay
)

fun Pelicula.toMovieEntity() = MovieEntity(
   cartel = cartel,
   releaseDate = fechaEstreno,
   movieId = idEspectaculo,
   title = titulo,
   originalTitle = tituloOriginal,
   background = background,
)

fun Pelicula.toUpcomingMoviesEntity() = UpcomingMoviesEntity(
   cartel = cartel,
   releaseDate = fechaEstreno,
   movieId = idEspectaculo,
   title = titulo,
   originalTitle = tituloOriginal,
   background = background,
)

fun Sesion.toSessionEntity() = SessionEntity(
   length = duracion,
   spanishReleaseDate = fechaEstrenoSpanish,
   hour = hora,
   movieId = idEspectaculo,
   sessionId = idPase,
   roomId = idSala,
   roomName = nombreSala,
   format = nombreFormato,
   interpreters = interpretes,
   rating = nombreCalificacion,
   gender = nombreGenero,
   synopsis = sinopsis,
   title = titulo,
   originalTitle = tituloOriginal,
   video = video,
   cartel = cartel,
   fullDay = diacompleto
)

fun InfoCine?.orElse(alternateObject: InfoCine): InfoCine = alternateObject

fun SesionRemote.toDomain() = Sesion(
   duracion = duracion,
   fechaEstrenoSpanish = fechaEstrenoSpanish,
   hora = hora,
   idEspectaculo = iDEspectaculo,
   idPase = iDPase,
   idSala = iDSala,
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

fun SessionWithBackground.toDomain() = Sesion(
   duracion = session.length,
   fechaEstrenoSpanish = session.spanishReleaseDate,
   hora = session.hour,
   idEspectaculo = session.movieId,
   idPase = session.sessionId,
   idSala = session.roomId,
   nombreSala = session.roomName,
   nombreFormato = session.format,
   interpretes = session.interpreters,
   nombreCalificacion = session.rating,
   nombreGenero = session.gender,
   sinopsis = session.synopsis,
   titulo = session.title,
   tituloOriginal = session.originalTitle,
   video = session.video,
   cartel = session.cartel.getUrlCartel(),
   diacompleto = session.fullDay,
   background = background
)

fun PeliculaRemote.toDomain() = Pelicula(
   cartel = cartel.getUrlCartel(),
   fechaEstreno = fechaEstreno,
   idEspectaculo = idEspectaculo,
   titulo = titulo,
   tituloOriginal = tituloOriginal
)

fun InfoCine.toDomain() = Cartelera(
   peliculas = this.pelis.map { it.toDomain() },
   proximosEstrenos = this.proximosEstrenos?.let { estrenos -> estrenos.map { it.toDomain() } }
      ?: emptyList(),
   sesiones = this.sesiones.map { it.toDomain() }
)

fun String.getUrlCartel(): String =
   this.takeIf { it.startsWith("https") } ?: (RUTA_CARTELES + this)