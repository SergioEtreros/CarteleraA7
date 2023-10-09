package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.senkou.carteleraa7.presentation.theme.resalte_ticket
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavHostController, model: PeliViewModel = viewModel()) {

    val tabIndex = model.tabindex.observeAsState().value
    val pagerState =  rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    val tabData = listOf(
        "CARTELERA",
        "PROX. ESTRENOS"
    )
//    val tabIndex = model.tabindex.observeAsState().value
//    val pagerState = rememberPagerState(
//        initialPage = 0,
//        initialPageOffsetFraction = 0f,
//    ) {
//        tabData.size
//    }
//    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxWidth()) {
        tabIndex?.let {
            TabRow(selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator( modifier = Modifier.tabIndicatorOffset(
                    currentTabPosition = tabPositions[tabIndex],
                ))
            }) {
                tabData.forEachIndexed { index, title ->
                    Tab(modifier = Modifier.background(color = resalte_ticket),
                        selected = tabIndex == index,
                        onClick = { model.actualizarTabIndex(index)
                            coroutineScope.launch {
                                // Call scroll to on pagerState
                                pagerState.scrollToPage(index)
                            }
                        },
                        text = { Text(text = title) })
                }
            }


            HorizontalPager(
                pageCount = tabData.size,
                state = pagerState,
            ) { tabIndex ->
                model.actualizarTabIndex(tabIndex)
                NavegarTabs(navController = navController, model = model , tabIndex = tabIndex)
            }

//            HorizontalPager(
//                state = pagerState,
//            ){ page ->
//                model.actualizarTabIndex(page)
//                NavegarTabs(navController = navController, model = model, tabIndex = page)
//            }
        }
    }
}

@Composable
private fun NavegarTabs(navController: NavHostController, model: PeliViewModel, tabIndex: Int){
    when (tabIndex){
        0-> Cartelera(navController = navController, model = model)
        1-> ProximosEstrenos(navController = navController, model = model)
    }
}

@Preview(showSystemUi = false)
@Composable
fun MainScreenPreview() {
    val model: PeliViewModel = viewModel()
//    model.dataA7 = DataA7(RepoWeb())
//    model.cargarCartelera()
    MainScreen(rememberNavController(), model)
}