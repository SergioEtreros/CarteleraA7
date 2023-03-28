package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.model.Peli

interface IRepository {
    fun obtenerCartelera():MutableList<Peli>
}