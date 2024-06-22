package com.senkou.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.senkou.wear.navigation.AppNavitagion
import com.senkou.wear.ui.theme.CarteleraA7Theme

class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)

      setContent {
         CarteleraA7Theme {
            AppNavitagion()
         }
      }
   }
}