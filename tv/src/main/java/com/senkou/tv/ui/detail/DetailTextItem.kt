package com.senkou.tv.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Text
import com.senkou.tv.ui.theme.Typography

@Composable
fun DetailTextItem(title: String, text: String) {
   Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
      Text(text = title, fontWeight = FontWeight.Bold, style = Typography.titleLarge, color = Color.White)
      Text(text = text, style = Typography.bodyLarge, color = Color.White, textAlign = TextAlign.Justify)
   }
}