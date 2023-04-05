package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.model.Json4KotlinBase

interface IRepository {
    fun obtenerCartelera(): Json4KotlinBase?
}