package com.senkou.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.senkou.tv.ui.navigation.AppNavitagion
import com.senkou.tv.ui.theme.CarteleraA7Theme

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

