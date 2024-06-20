package com.senkou.wear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.senkou.wear.navigation.AppNavitagion
import com.senkou.wear.ui.screens.mainscreen.PeliViewModel
import com.senkou.wear.ui.theme.CarteleraA7Theme

class MainActivity : ComponentActivity() {

   private val model: PeliViewModel by viewModels()

   override fun onCreate(savedInstanceState: Bundle?) {
//      installSplashScreen()

      super.onCreate(savedInstanceState)

      setTheme(android.R.style.Theme_DeviceDefault)

      setContent {
         CarteleraA7Theme {
            AppNavitagion(model)
         }
      }
   }
}