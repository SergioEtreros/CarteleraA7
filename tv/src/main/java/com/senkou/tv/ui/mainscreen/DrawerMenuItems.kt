package com.senkou.tv.ui.mainscreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.tv.material3.Icon
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.NavigationDrawerItemDefaults
import androidx.tv.material3.NavigationDrawerScope
import androidx.tv.material3.Text
import com.senkou.tv.R
import com.senkou.tv.ui.theme.fondo_lista
import com.senkou.tv.ui.theme.resalte_ticket

@Composable
fun NavigationDrawerScope.DrawerMenuItems(
   selectedIndex: Int,
   onClick: (index: Int) -> Unit
) {
   val tabs = listOf(
      "CARTELERA" to ImageVector.vectorResource(R.drawable.baseline_movie_24),
      "PROX. ESTRENOS" to Icons.Default.DateRange
   )

   tabs.forEachIndexed { index, item ->
      val (text, icon) = item

      NavigationDrawerItem(
         selected = selectedIndex == index,
         onClick = { onClick(index) },
         colors = NavigationDrawerItemDefaults.colors(
            contentColor = Color.White,
            inactiveContentColor = Color.White,
            selectedContentColor = Color.White,
            selectedContainerColor = resalte_ticket,
            focusedContainerColor = fondo_lista,
            focusedContentColor = Color.White
         ),
         leadingContent = {
            Icon(
               imageVector = icon,
               contentDescription = null,
            )
         }
      ) { Text(text) }
   }
}