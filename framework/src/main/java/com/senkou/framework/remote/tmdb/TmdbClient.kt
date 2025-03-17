package com.senkou.framework.remote.tmdb

import com.senkou.framework.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

internal class TmdbClient(baseUrl: String) {

   private val intercepter = HttpLoggingInterceptor().apply {
      level = HttpLoggingInterceptor.Level.HEADERS
   }

   private val okHttpClient = OkHttpClient.Builder()
      .addInterceptor(intercepter)
      .addInterceptor(::apiKeyAsQuery)
      .build()

   private val json = Json {
      ignoreUnknownKeys = true
   }

   val instance = Retrofit.Builder()
      .baseUrl(baseUrl)
      .client(okHttpClient)
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .build()
      .create<TmdbService>()
}

private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
   chain.request()
      .newBuilder()
      .url(
         chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .build()
      )
      .build()
)