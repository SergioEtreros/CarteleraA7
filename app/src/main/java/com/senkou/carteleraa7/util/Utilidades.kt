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

            val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$youtubeID"))
            val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$youtubeID"))
            try {
                context.startActivity(intentApp)
            } catch (ex: ActivityNotFoundException) {
                context.startActivity(intentBrowser)
            }

        }
    }
}