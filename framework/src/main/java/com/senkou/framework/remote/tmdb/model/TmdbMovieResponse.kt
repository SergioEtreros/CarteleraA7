package com.senkou.framework.remote.tmdb.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TmdbMovieResponse(
   @SerialName("page")
   val page: Int = 0,
   @SerialName("results")
   val results: List<TmdbMovie> = listOf(),
   @SerialName("total_pages")
   val totalPages: Int = 0,
   @SerialName("total_results")
   val totalResults: Int = 0
)