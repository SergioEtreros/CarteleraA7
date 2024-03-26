package com.senkou.carteleraa7.framework.ui.common

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

class RoundedCutoutShape(
   private val offset: Float?,
   private val cornerRadiusDp: Dp,
   private val orientation: Orientation = Orientation.Horizontal
) : Shape {
   override fun createOutline(
      size: Size,
      layoutDirection: LayoutDirection,
      density: Density,
   ) = Outline.Generic(run path@{
      val cornerRadius = with(density) { cornerRadiusDp.toPx() }
      val rect = Rect(Offset.Zero, size)
      val mainPath = Path().apply {
         addRoundRect(RoundRect(rect, CornerRadius(cornerRadius)))
      }
      if (offset == null) return@path mainPath
      val cutoutPath = Path().apply {
         val circleSize = Size(cornerRadius, cornerRadius) * 2f
         val visiblePart = 0.25f

         when (orientation) {
            Orientation.Horizontal -> {
               val topOval = Rect(
                  offset = Offset(
                     x = offset - circleSize.height / 2,
                     y = 0 - circleSize.width * (1 - visiblePart)
                  ),
                  size = circleSize
               )
               val bottomOval = Rect(
                  offset = Offset(
                     x = offset - circleSize.height / 2,
                     y = rect.height - circleSize.width * visiblePart
                  ),
                  size = circleSize
               )

               addOval(topOval)
               addOval(bottomOval)
            }

            Orientation.Vertical -> {
               val leftOval = Rect(
                  offset = Offset(
                     x = 0 - circleSize.width * (1 - visiblePart),
                     y = offset - circleSize.height / 2
                  ),
                  size = circleSize
               )
               val rightOval = Rect(
                  offset = Offset(
                     x = rect.width - circleSize.width * visiblePart,
                     y = offset - circleSize.height / 2
                  ),
                  size = circleSize
               )

               addOval(leftOval)
               addOval(rightOval)
            }
         }
      }
      return@path Path().apply {
         op(mainPath, cutoutPath, PathOperation.Difference)
      }
   })
}