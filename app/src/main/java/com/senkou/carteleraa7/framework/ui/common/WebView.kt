package com.senkou.carteleraa7.framework.ui.common

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebView(url: String) {
   AndroidView(
      modifier = Modifier
         .fillMaxWidth()
         .height(200.dp),
      factory = {
         WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
         }
      })
}

@Preview
@Composable
fun WebViewPreview() {
   WebView(url = "https://artesiete.es/Sesion/13/ARTESIETE-Segovia/BARBIE/14808/11279")
}