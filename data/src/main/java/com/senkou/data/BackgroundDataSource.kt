package com.senkou.data

interface BackgroundDataSource {
   suspend fun getMovieBackgroundByName(movieName: String, year: String): String?
}