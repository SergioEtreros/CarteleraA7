package com.senkou.tv.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubeView(
   videoId: String,
   lifecycleOwner: LifecycleOwner
) {
   AndroidView(
      modifier = Modifier.fillMaxSize(),
      factory = { context ->

         YouTubePlayerView(context).apply {
            lifecycleOwner.lifecycle.addObserver(this)

            addYouTubePlayerListener(youTubePlayerListener = object :
               AbstractYouTubePlayerListener() {
               override fun onReady(youTubePlayer: YouTubePlayer) {
                  youTubePlayer.loadVideo(videoId, 0f)
               }
            }
            )
         }
      },
   )
}