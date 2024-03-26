package com.senkou.carteleraa7.framework.ui.mainscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.senkou.carteleraa7.framework.navigation.AppNavitagion
import com.senkou.carteleraa7.framework.theme.CarteleraA7Theme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
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