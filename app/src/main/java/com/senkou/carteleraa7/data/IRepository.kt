package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.data_clases.Peli

interface IRepository {
    fun obtenerCartelera():MutableList<Peli>
}