package com.senkou.framework.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Pelicula(
   @SerializedName("Cartel")
   val cartel: String,
   @SerializedName("FechaEstreno")
   val fechaEstreno: String,
   @SerializedName("ID_Espectaculo")
   val iDEspectaculo: Int,
   @SerializedName("Titulo")
   val titulo: String
)