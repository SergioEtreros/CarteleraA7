package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.senkou.carteleraa7.R
import com.senkou.carteleraa7.data.DataA7
import com.senkou.carteleraa7.navigation.AppScreens
import com.senkou.carteleraa7.presentation.theme.fondoLogo
import com.senkou.carteleraa7.repository.RepoWeb

@Composable
fun SplashScreen(navController: NavHostController, model: PeliViewModel) {

    Splash()

    model.dataA7 = DataA7(RepoWeb())
    model.cargarCartelera()
//    val lista = model.peliculas.observeAsState().value
    model.peliculas.observe(LocalLifecycleOwner.current){
//        Log.d("Cartelera", "Cambiando estado de la cartelera")
        navController.popBackStack()
        navController.navigate(AppScreens.MainScreen.route)
    }
}

@Composable
fun Splash(){
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoLogo),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
            id = R.drawable.ic_launcher_foreground),
            contentDescription = "Splash")
        
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    Splash()
}