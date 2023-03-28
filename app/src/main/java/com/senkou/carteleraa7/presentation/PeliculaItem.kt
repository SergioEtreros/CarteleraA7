package com.senkou.carteleraa7.presentation

//import androidx.compose.material.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.senkou.carteleraa7.data.DataA7
import com.senkou.carteleraa7.data.model.Peli
import com.senkou.carteleraa7.navigation.AppScreens
import com.senkou.carteleraa7.presentation.theme.Typography
import com.senkou.carteleraa7.presentation.theme.fondoFechaEstreno
import com.senkou.carteleraa7.presentation.theme.fondoLogo
import com.senkou.carteleraa7.repository.RepoWeb
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun PeliculaItem(navController: NavHostController, data: Peli, index: Int){

//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { navController.navigate("${AppScreens.DetalleScreen.route}/$index") },
//        RoundedCornerShape(15.dp),
//        backgroundColor = Color.White,
//        elevation = 5.dp,
//    ) {
//
//        Column(modifier = Modifier
//            .padding(8.dp)
//            .fillMaxSize()
//        ){
//
//            Text(modifier = Modifier
//                .fillMaxWidth()
//                .background(
//                    color = resalte_ticket,
//                    shape = RoundedCornerShape(5.dp)
//                )
//                .padding(2.dp),
//                maxLines = 1,
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                style = Typography.subtitle1,
//                text = data.titulo)
//
//            Spacer(modifier = Modifier.height(8.dp))

            val shape = RoundedCornerShape(15.dp)

            Box (modifier = Modifier
                .shadow(35.dp, shape, spotColor = Color.Black)
                .clip(shape)
                .border(2.dp, fondoLogo, shape)
                .clickable {
                navController.navigate("${AppScreens.DetalleScreen.route}/$index")
            }) {
                SubcomposeAsyncImage(
//                    model = "https://artesiete.es${data.urlImagen}",
                    model = data.urlImagen,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()

                        ,
                    alignment = Alignment.TopCenter,
                    loading = { CircularProgressIndicator() },
                    contentDescription = data.titulo)

                if (data.fechaEstreno.isNotEmpty() ) {
                    val parser = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val fecha = parser.parse(parser.format(Date()))
                    val fechaEstreno = parser.parse(data.fechaEstreno)
                    if (fechaEstreno != null && fecha != null) {
                        if (fechaEstreno >= fecha) {
                            Text(modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .clip(RoundedCornerShape(15.dp, 15.dp))
                                .background(fondoFechaEstreno),
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                style = Typography.body2,
                                text = data.fechaEstreno
                            )
                        }
                    }
                }
            }
//        }
//    }
}

@Preview(showSystemUi = false)
@Composable
fun PeliculaItemPreview() {
    val model = PeliViewModel()

    model.dataA7 = DataA7(RepoWeb())
    model.cargarCartelera()
    val lista = model.peliculas.observeAsState().value

    lista?.let {
        val peli = it[0]
        PeliculaItem(rememberNavController(), peli, 0)
    }
}