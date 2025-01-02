package com.senkou.framework.remote.tmdb

import com.senkou.data.BackgroundDataSource

class TmdbServerDataSource(
   private val tmdbService: TmdbService
) : BackgroundDataSource {
   override suspend fun getMovieBackgroundByName(movieName: String, year: String): String? {
      try {
         val response = tmdbService.getMovieByName(movieName, year)
         return response.results.first().backdropPath.toUrl()
      } catch (e: Exception) {
         e.printStackTrace()
         return null
      }
   }
}

private fun String?.toUrl(): String? = this?.let { "https://image.tmdb.org/t/p/original$this" }
