package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.model.InfoCine

interface IRepository {
    fun obtenerCartelera(): InfoCine?
}