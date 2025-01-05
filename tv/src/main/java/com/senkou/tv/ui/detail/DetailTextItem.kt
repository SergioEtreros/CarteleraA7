package com.senkou.tv.ui.detail

import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.senkou.tv.ui.Screen
import com.senkou.tv.ui.theme.Typography

@Composable
fun DetailTextItem(title: String, text: String) {

   val focusRequester = remember { FocusRequester() }
   val interactionSource = remember { MutableInteractionSource() }
   val isFocused = interactionSource.collectIsFocusedAsState().value

   Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
      Text(
         modifier = Modifier
            .focusRequester(focusRequester)
            .focusable(interactionSource = interactionSource),
         text = title,
         fontWeight = FontWeight.Bold,
         style = Typography.titleLarge,
         color = Color.White,
         textDecoration = if (isFocused) TextDecoration.Underline else null
      )

      Text(
         text = text,
         style = Typography.bodyLarge,
         color = Color.White,
         textAlign = TextAlign.Justify
      )
   }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailTextItem() {
   Screen {
      DetailTextItem("Título original", "The Flash")
   }
}