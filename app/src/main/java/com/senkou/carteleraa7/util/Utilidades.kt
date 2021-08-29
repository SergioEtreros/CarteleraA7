package com.senkou.carteleraa7.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class Utilidades {
    companion object{

        fun playTrailer(context: Context, urlTrailer: String){
            when {
                urlTrailer.contains("youtu.be") -> {
                    val videoId = urlTrailer.substringAfter("youtu.be/")
                    openYoutubeLink(context, videoId)
                }
                urlTrailer.contains("youtube") -> {

                    val videoId = urlTrailer.substringAfter("v=")
                    openYoutubeLink(context, videoId)
                }
                else -> {
                    Toast.makeText(context, "No se reconoce la ID del vídeo", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun openYoutubeLink(context: Context, youtubeID: String) {

            val id = if (youtubeID.contains ("youtube")) {
                youtubeID.substring(youtubeID.lastIndexOf("/")+1) }
            else {
                youtubeID
            }

            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$id"))
            try {
                context.startActivity(intentApp)
            } catch (ex: ActivityNotFoundException) {
                context.startActivity(intentBrowser)
            }
        }

        fun ordenarMeses(fechasSpinner: ArrayList<String>) {
            fechasSpinner.sort()
            val primero = fechasSpinner.first()

            val index = fechasSpinner.indexOf(
                fechasSpinner.find {element->
                    element.substring(element.indexOf('/')+1, element.lastIndexOf('/')) <
                    primero.substring(primero.indexOf('/')+1, primero.lastIndexOf('/'))
            })

            for (i in 1 ..index)
            {
                fechasSpinner.add(fechasSpinner.first())
                fechasSpinner.removeFirst()
            }
        }
    }
}