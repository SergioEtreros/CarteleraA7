package com.senkou.carteleraa7.data.model

import com.google.gson.annotations.SerializedName

data class Json4KotlinBase(
        @SerializedName("Cartelera") val pelis: List<Peli>,
        @SerializedName("Proximamente") val proximosEstrenos: List<ProximoEstreno>
)