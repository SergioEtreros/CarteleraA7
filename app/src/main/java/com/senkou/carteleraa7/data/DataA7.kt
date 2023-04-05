package com.senkou.carteleraa7.data

import com.senkou.carteleraa7.data.model.Json4KotlinBase

class DataA7(private val repo: IRepository) {

    fun obtenerCartelera(): Json4KotlinBase? {
        return repo.obtenerCartelera()
    }
}