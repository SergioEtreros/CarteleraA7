package com.senkou.carteleraa7.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senkou.carteleraa7.presentation.DetallePelicula
import com.senkou.carteleraa7.presentation.MainScreen
import com.senkou.carteleraa7.presentation.PeliViewModel
import com.senkou.carteleraa7.presentation.SplashScreen

@Composable
fun AppNavitagion(model: PeliViewModel = viewModel()){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.SplashScreen.route
    ) {
        composable(AppScreens.SplashScreen.route){
            SplashScreen(navController, model)
        }
        composable(AppScreens.MainScreen.route){
            MainScreen(navController, model)
        }
        composable(
            route = "${AppScreens.DetalleScreen.route}/{itemId}",
            arguments = listOf(navArgument("itemId"){type = NavType.IntType})
        ){navBackStackEntry->
            val id = navBackStackEntry.arguments?.getInt("itemId", -1)
            id?.let {
                if (id >= 0){
                    model.peliculas.value?.let {lista->
                        DetallePelicula(navController, lista[id])
                    }
                }
            }
        }
    }
}