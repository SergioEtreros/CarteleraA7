package com.senkou.data

import com.senkou.domain.model.Pelicula
import kotlinx.coroutines.flow.onEach

class MoviesRepository(
   private val localDataSource: LocalDataSource,
   private val webMovieDatasource: RemoteDataSource,
   private val backgroundDataSource: BackgroundDataSource
) {

   val peliculas
      get() = localDataSource.peliculas.onEach { peliculas ->
         if (peliculas.isEmpty()) {
            val cartelera = webMovieDatasource.getCartelera()
            localDataSource.savePeliculas(cartelera.peliculas.addmoviesBackground(backgroundDataSource))
            localDataSource.saveProximosEstrenos(
               cartelera.proximosEstrenos.addmoviesBackground(
                  backgroundDataSource
               )
            )
            localDataSource.saveSesiones(cartelera.sesiones)
         }
      }

   val proximosEstrenos
      get() = localDataSource.proximosEstrenos


   fun getSesiones(idEspectaculo: Int) = localDataSource.getSesiones(idEspectaculo)

}

private suspend fun List<Pelicula>.addmoviesBackground(backgroundDataSource: BackgroundDataSource) =
   map {
      it.copy(
         background = backgroundDataSource.getMovieBackgroundByName(
            it.tituloOriginal,
            it.fechaEstreno.substringBefore("-")
         ) ?: it.cartel
      )
   }

