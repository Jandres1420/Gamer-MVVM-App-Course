package com.mvvm.gamermvvmapp.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mvvm.gamermvvmapp.R
import com.mvvm.gamermvvmapp.presentation.MainActivity
import com.mvvm.gamermvvmapp.presentation.components.DefaultButton
import com.mvvm.gamermvvmapp.presentation.navigation.AuthScreen
import com.mvvm.gamermvvmapp.presentation.navigation.DetailsScreen
import com.mvvm.gamermvvmapp.presentation.navigation.Graph

import com.mvvm.gamermvvmapp.presentation.screens.profile.ProfileViewModel
import com.mvvm.gamermvvmapp.presentation.ui.theme.Darkgray500

@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    val activity = LocalContext.current as? Activity

    // column es como linear layout
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Darkgray500),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // para elementos uno encima del otro se usa Box
        Box(){
//            con fill max width ocupara todo el ancho de la imagen pero si se necesita más para cubrir la pantalla usaremos contentScale = ContentScale.Crop
            // con alpha se pone la transparencia de la imagen y la escala es de 0 - 1
            Image(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp), painter = painterResource(id = R.drawable.background)
                , contentDescription = "",
                contentScale = ContentScale.Crop
                , alpha = 0.6f )
            Column( modifier =  Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Bienvenido"
                    , fontSize = 30.sp
                    , fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(55.dp))
                if(viewModel.userData.image != ""){
                    AsyncImage(
                        modifier = Modifier.size(115.dp).clip(CircleShape),
                        model = viewModel.userData.image,
                        contentDescription = "User Image",
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Image(modifier = Modifier.size(115.dp),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "")
                }

            }
        }
        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = viewModel.userData.username,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = viewModel.userData.email,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(20.dp))
        DefaultButton(modifier = Modifier.width(250.dp),
            text = "Editar datos"
            ,color = Color.White,
            onClick = {
                // ACA NOS ASEGURAMOS QUE LA RUTA NO ESTE CONFUNDEINDOSE CON LA URL DE LA IMAGEN DEL USUARIO
                // DICIENDO QUE ESA URL QUE ESTA EN USERDATA NO AFECTA A LA RUTA
//                viewModel.userData.image = URLEncoder.encode(viewModel.userData.image, StandardCharsets.UTF_8.toString())
                // AL PASAR UN PARAMETRO POR LA RUTA USAMOS EL METODO passUser, que convertira el objeto User a un String por JSON
                navController.navigate(route = DetailsScreen.ProfileEdit.passUser(viewModel.userData.toJson()))
            },
            icon = Icons.Default.Edit
            )
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButton(modifier = Modifier.width(250.dp)
            , text = "Cerrar sesion"
            , onClick = {
                viewModel.logOut()
//                IMPORTANTE
//                no se puede mandar como ruta directa el AuthScreen.Login.route, ya que no hay relacion entre el grafo home y el authentication
//                el grafo que tiene relación entre todos el el ROOT
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }
            )

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun previewProfileContent() {
//    GamerMVVMAppTheme {
//        GamerMVVMAppTheme(darkTheme = true) {
//            // A surface container using the 'background' color from the theme
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = MaterialTheme.colorScheme.background
//
//            ) {
//                ProfileContent()
//            }
//        }
//    }
//}
