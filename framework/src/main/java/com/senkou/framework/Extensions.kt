package com.senkou.framework

import com.senkou.domain.model.Cartelera
import com.senkou.domain.model.Pelicula
import com.senkou.domain.model.Sesion
import com.senkou.framework.local.room.SesionConFondo
import com.senkou.framework.local.room.entities.PeliculaEntity
import com.senkou.framework.local.room.entities.ProximoEstrenoEntity
import com.senkou.framework.local.room.entities.SesionEntity
import com.senkou.framework.remote.arte7.model.InfoCine
import com.senkou.framework.remote.arte7.model.PeliculaRemote
import com.senkou.framework.remote.arte7.model.SesionRemote

const val RUTA_CARTELES = "https://artesiete.es/Posters/"

fun PeliculaEntity.toDomain() = Pelicula(
   cartel = cartel,
   fechaEstreno = fechaEstreno,
   idEspectaculo = idEspectaculo,
   titulo = titulo,
   tituloOriginal = tituloOriginal,
   background = background,
)

fun ProximoEstrenoEntity.toDomain() = Pelicula(
   cartel = cartel,
   fechaEstreno = fechaEstreno,
   idEspectaculo = idEspectaculo,
   titulo = titulo,
   tituloOriginal = tituloOriginal,
   background = background,
)

fun SesionEntity.toDomain() = Sesion(
   duracion = duracion,
   fechaEstrenoSpanish = fechaEstrenoSpanish,
   hora = hora,
   idEspectaculo = idEspectaculo,
   idPase = idPase,
   idSala = idSala,
   nombreSala = nombreSala,
   nombreFormato = nombreFormato,
   interpretes = interpretes,
   nombreCalificacion = nombreCalificacion,
   nombreGenero = nombreGenero,
   sinopsis = sinopsis,
   titulo = titulo,
   tituloOriginal = tituloOriginal,
   video = video,
   cartel = cartel,
   diacompleto = diacompleto
)

fun Pelicula.toPeliculaEntity() = PeliculaEntity(
   cartel = cartel,
   fechaEstreno = fechaEstreno,
   idEspectaculo = idEspectaculo,
   titulo = titulo,
   tituloOriginal = tituloOriginal,
   background = background,
)

fun Pelicula.toProximoEstrenoEntity() = ProximoEstrenoEntity(
   cartel = cartel,
   fechaEstreno = fechaEstreno,
   idEspectaculo = idEspectaculo,
   titulo = titulo,
   tituloOriginal = tituloOriginal,
   background = background,
)

fun Sesion.toSesionEntity() = SesionEntity(
   duracion = duracion,
   fechaEstrenoSpanish = fechaEstrenoSpanish,
   hora = hora,
   idEspectaculo = idEspectaculo,
   idPase = idPase,
   idSala = idSala,
   nombreSala = nombreSala,
   nombreFormato = nombreFormato,
   interpretes = interpretes,
   nombreCalificacion = nombreCalificacion,
   nombreGenero = nombreGenero,
   sinopsis = sinopsis,
   titulo = titulo,
   tituloOriginal = tituloOriginal,
   video = video,
   cartel = cartel,
   diacompleto = diacompleto
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

fun SesionConFondo.toDomain() = Sesion(
   duracion = sesion.duracion,
   fechaEstrenoSpanish = sesion.fechaEstrenoSpanish,
   hora = sesion.hora,
   idEspectaculo = sesion.idEspectaculo,
   idPase = sesion.idPase,
   idSala = sesion.idSala,
   nombreSala = sesion.nombreSala,
   nombreFormato = sesion.nombreFormato,
   interpretes = sesion.interpretes,
   nombreCalificacion = sesion.nombreCalificacion,
   nombreGenero = sesion.nombreGenero,
   sinopsis = sesion.sinopsis,
   titulo = sesion.titulo,
   tituloOriginal = sesion.tituloOriginal,
   video = sesion.video,
   cartel = sesion.cartel.getUrlCartel(),
   diacompleto = sesion.diacompleto,
   background = fondo
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
      ?: emptyList()
)

fun String.getUrlCartel(): String =
   this.takeIf { it.startsWith("https") } ?: (RUTA_CARTELES + this)