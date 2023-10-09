package com.senkou.carteleraa7.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.senkou.carteleraa7.data.model.Sesion
import com.senkou.carteleraa7.presentation.theme.Typography
import com.senkou.carteleraa7.presentation.theme.fondoLogo

@Composable
fun InfoSesiones(urlImagen:String, fecha: String, sesiones: List<Sesion>) {

    val shape = RoundedCutoutShape(offset = 287.0F, 10.dp, Orientation.Horizontal)

    Row(
        modifier = Modifier
            .width(250.dp)
            .height(140.dp)
            .background(
                color = Color.White,
                shape = shape
            )
            .border(1.dp, fondoLogo, shape)
            .padding(12.dp, 12.dp),
    ) {
        SubcomposeAsyncImage(
            model = urlImagen,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .clip(RoundedCornerShape(5.dp)),
            contentDescription = "",
            loading = { CircularProgressIndicator() }
        )

        Spacer(modifier = Modifier.width(12.dp))

        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        Canvas(Modifier.fillMaxHeight().width(2.dp)) {

            drawLine(
                color = Color.Gray,
                start = Offset(0f, 0f),
                end = Offset( 0f, size.height),
                pathEffect = pathEffect
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = Color.Black,
                textAlign = TextAlign.Start,
                style = Typography.subtitle2,
                text = fecha)

            Spacer(modifier = Modifier.width(3.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = Color.Black,
                textAlign = TextAlign.Start,
                style = Typography.body2,
                text = sesiones.first().crearTextoSesiones(sesiones)
            )
        }
    }
}

@Preview
@Composable
fun PreviewInfoSesiones(){
//    InfoSesiones("", "09/03/2023", "sdfsdfsfsfd")
}