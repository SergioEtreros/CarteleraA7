package com.senkou.carteleraa7.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.senkou.carteleraa7.navigation.AppNavitagion
import com.senkou.carteleraa7.presentation.theme.CarteleraA7Theme


class MainActivity : ComponentActivity() {

    private val model: PeliViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarteleraA7Theme {
                AppNavitagion(model)
            }
        }
   }
}