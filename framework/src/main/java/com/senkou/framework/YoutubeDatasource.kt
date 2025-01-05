package com.senkou.framework

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.senkou.data.PlayVideoDataSource

class YoutubeDatasource(private val context: Context) : PlayVideoDataSource {
   override fun playTrailer(urlTrailer: String) {
      Log.d("Youtube", "urlTrailer: $urlTrailer")
      when {
         urlTrailer.contains("youtu.be") -> {
            val videoId = urlTrailer.substringAfter("youtu.be/")
            Log.d("Youtube", "videoId: $videoId")
            openYoutubeLink(context, videoId)
         }

         urlTrailer.contains("youtube") -> {
            val videoId = urlTrailer.substringAfter("v=")
            Log.d("Youtube", "videoId: $videoId")
            openYoutubeLink(context, videoId)
         }

         else -> {
            Toast.makeText(context, "No se reconoce la ID del vídeo", Toast.LENGTH_SHORT).show()
         }
      }
   }

   private fun openYoutubeLink(context: Context, youtubeID: String) {

      val id = if (youtubeID.contains("youtube")) {
         youtubeID.substringAfterLast("/")
      } else {
         youtubeID
      }

      Log.d("Youtube", "id: $id")

      try {
         val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$id"))
         intentApp.putExtra("force_fullscreen", true)
         intentApp.putExtra("finish_on_ended", true)
         intentApp.putExtra("autoplay", true)

         Log.d("Youtube", "intentApp: ${intentApp.data}")
         context.startActivity(intentApp)
      } catch (ex: ActivityNotFoundException) {
         try {
            val intentBrowser =
               Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/embed/$id"))
            intentBrowser.putExtra("force_fullscreen", true)
            intentBrowser.putExtra("finish_on_ended", true)
            intentBrowser.putExtra("autoplay", true)

            Log.d("Youtube", "intentApp WEB: ${intentBrowser.data}")
            context.startActivity(intentBrowser)
         } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context, "No se pudo abrir el video", Toast.LENGTH_SHORT).show()
         }
      }

   }
}