package com.senkou.carteleraa7.framework.ui.mainscreen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.senkou.carteleraa7.framework.theme.resalte_ticket
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavHostController, model: PeliViewModel = hiltViewModel()) {

   var tabIndex by remember { mutableIntStateOf(0) }
   val coroutineScope = rememberCoroutineScope()

   val tabData = listOf(
      "CARTELERA",
      "PROX. ESTRENOS"
   )

   val pagerState = rememberPagerState(
      initialPage = 0,
      initialPageOffsetFraction = 0f,
   ) {
      tabData.size
   }

   Column(modifier = Modifier.fillMaxWidth()) {
      TabRow(selectedTabIndex = tabIndex,
         indicator = { tabPositions ->
            TabRowDefaults.Indicator(
               modifier = Modifier.tabIndicatorOffset(
                  currentTabPosition = tabPositions[tabIndex],
               )
            )
         }) {
         tabData.forEachIndexed { index, title ->
            Tab(modifier = Modifier.background(color = resalte_ticket),
               selected = tabIndex == index,
               onClick = {
                  coroutineScope.launch {
                     // Call scroll to on pagerState
                     pagerState.scrollToPage(index)
                  }
               },
               text = { Text(text = title) })
         }
      }

      HorizontalPager(
         state = pagerState,
      ) { page ->
         tabIndex = page
         NavegarTabs(navController = navController, model, tabIndex = page)
      }
   }
}

@Composable
private fun NavegarTabs(navController: NavHostController, model: PeliViewModel, tabIndex: Int) {
   when (tabIndex) {
      0 -> Cartelera(navController = navController, model.getPeliculas())
      1 -> Cartelera(navController = navController, model.getProximosEstrenos())
//      1 -> ProximosEstrenos(navController = navController, model)
   }
}

@Preview(showSystemUi = false)
@Composable
fun MainScreenPreview() {
   MainScreen(rememberNavController())
}