package com.mvvm.gamermvvmapp.presentation.screens.profile.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mvvm.gamermvvmapp.R
import com.mvvm.gamermvvmapp.presentation.components.DefaultButton
import com.mvvm.gamermvvmapp.presentation.navigation.AppScreen

import com.mvvm.gamermvvmapp.presentation.screens.login.LoginScreen
import com.mvvm.gamermvvmapp.presentation.screens.profile.ProfileViewModel
import com.mvvm.gamermvvmapp.presentation.ui.theme.Darkgray500
import com.mvvm.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red500

@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    // column es como linear layout
    Column(modifier =  Modifier.fillMaxSize().background(Darkgray500),
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
                Image(modifier = Modifier.size(115.dp), painter = painterResource(id = R.drawable.user), contentDescription = "")
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
//                viewModel.logOut()
//                navController.navigate(AppScreen.Login.route){
//                    // esto eliminara la ruta anterior
//                    popUpTo(AppScreen.Profile.route){ inclusive = true}
//                }
            },
            icon = Icons.Default.Edit
            )
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButton(modifier = Modifier.width(250.dp)
            , text = "Cerrar sesion"
            , onClick = {
                viewModel.logOut()
                navController.navigate(AppScreen.Login.route){
                    // esto eliminara la ruta anterior
                    popUpTo(AppScreen.Profile.route){ inclusive = true}
                }
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
