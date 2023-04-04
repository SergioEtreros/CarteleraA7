package com.senkou.carteleraa7.data.model

import com.google.gson.annotations.SerializedName

class ResponseProximosEstrenos(
    @SerializedName("Proximamente") val proximosEstrenos: List<ProximoEstreno>
)