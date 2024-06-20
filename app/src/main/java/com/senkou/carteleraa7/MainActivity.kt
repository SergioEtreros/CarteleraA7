package com.senkou.carteleraa7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.senkou.carteleraa7.ui.navigation.AppNavitagion
import com.senkou.carteleraa7.ui.theme.CarteleraA7Theme
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      enableEdgeToEdge()

      setContent {
         CarteleraA7Theme {
            AppNavitagion()
         }
      }
   }
}