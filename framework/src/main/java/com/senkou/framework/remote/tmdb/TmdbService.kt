package com.senkou.framework.remote.tmdb

import com.senkou.framework.remote.tmdb.model.TmdbMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

   @GET("search/movie")
   suspend fun getMovieByName(
      @Query("query") movieName: String,
      @Query("year") year: String,
      @Query("include_adult") includeAdult: Boolean = true,
      @Query("language") language: String = "es-es",
      @Query("page") page: Int = 1
   ): TmdbMovieResponse
}