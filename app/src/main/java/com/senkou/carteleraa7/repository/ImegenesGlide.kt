package com.senkou.carteleraa7.repository

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.senkou.carteleraa7.data.data_clases.IImagenes

class ImegenesGlide(private val context: Context): IImagenes {
    override fun cargarImagen(view: ImageView, url: String) {
        Glide.with(context).load(url).into(view)
    }
}