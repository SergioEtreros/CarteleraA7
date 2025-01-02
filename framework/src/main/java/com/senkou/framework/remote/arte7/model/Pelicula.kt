package com.senkou.framework.remote.arte7.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Pelicula(
   @SerializedName("Cartel")
   val cartel: String,
   @SerializedName("FechaEstreno")
   val fechaEstreno: String,
   @SerializedName("ID_Espectaculo")
   val idEspectaculo: Int,
   @SerializedName("Titulo")
   val titulo: String,
   @SerializedName("TituloOriginal")
   val tituloOriginal: String
)