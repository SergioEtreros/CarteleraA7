package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.senkou.carteleraa7.presentation.theme.resalte_ticket

@Composable
fun MainScreen(navController: NavHostController, model: PeliViewModel = viewModel()) {

//    var tabIndex by remember { mutableStateOf(0) }
//    var tabIndex = model.getTabIndex()

    val tabIndex = model.tabindex.observeAsState().value

    val tabData = listOf(
        "CARTELERA",
        "PROX.ESTRENOS"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        tabIndex?.let {
            TabRow(selectedTabIndex = tabIndex) {
                tabData.forEachIndexed { index, text ->
                    Tab(modifier = Modifier.background(color = resalte_ticket),
                        selected = tabIndex == index,
                        onClick = {
                            model.actualizarTabIndex(index)
                        },
                        text = {
                            Text(text = text)
                        })
                }
            }
            when (tabIndex){
                0->Cartelera(navController = navController, model = model)
                1-> ProximosEstrenos(navController = navController, model = model)
            }
        }
    }


//    Cartelera(navController = navController, model = model)
}

@Preview(showSystemUi = false)
@Composable
fun MainScreenPreview() {
    val model: PeliViewModel = viewModel()
//    model.dataA7 = DataA7(RepoWeb())
//    model.cargarCartelera()
    MainScreen(rememberNavController(), model)
}