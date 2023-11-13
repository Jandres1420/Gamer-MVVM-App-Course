package com.mvvm.gamermvvmapp.presentation.screens.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mvvm.gamermvvmapp.R
import com.mvvm.gamermvvmapp.presentation.screens.login.components.LogInBottomBar
import com.mvvm.gamermvvmapp.presentation.screens.login.components.LogInContent
import com.mvvm.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LoginScreen(navController: NavHostController) {
    // es como el esqueleto
    // esat compuesto por topbar, content, bottombar


//    var viewModel = LogInViewModel() ESTOQUEDA DOCUMENTADO PORQUE NO DEBERIA SER USADO DE ESTA MANERA
    // SI NO POR MEDIO DE DAGGER
    Scaffold(
        topBar = {},
        content = {
//            BodyContent()
            LogInContent(navController)
                  },
        bottomBar = {
            LogInBottomBar(navController)
        }
    )

}


// ESTA ES UNA FORMA DE HACERLOLLAMANDO AL BODYCONTENT
// PERO PARA MÁS ORGANIZACIÓN ES MEJOR DEJAR ESTO EN OTRA CARPETA DE COMPONENTES
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(){
//    para modificar que todos los componentes tenganun atributo se usa modifier dentro del Column
    Column(
//        fil max width cubrira todo el ancho, wrapcontent hara que todo se ponga con respecto al tamaño que tenga
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    )  {
        Image(modifier = Modifier.height(130.dp), painter = painterResource(id = R.drawable.control)
            , contentDescription = "Control de XBOX 360")
        Text(text = "FIREBASE MVVM")
        // modifier hara varios cambios en diseño con respecto al ui como las properties xml
        // Text(modifier = Modifier.padding(top = 30.dp),text = "LOGIN") DE ESTA MANERA SOLO ABRIA UNA DISTANCIA HACIA ARRIBA DE 30DP, si no se pone top tendira separación hacia todos los lados

        Text(modifier = Modifier.padding(top = 30.dp, bottom = 0.dp, start = 0.dp, end = 0.dp),text = "LOGIN")
        // otra forma de hacer un padding es con Spacer(modifier = Modifier.height(10.dp))
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Por favor inicia sesion para continuar")
//        TextField es EditText, el parametro value sera la variable que va ha estar cambiando conforme se escriba, onValueChange el estado
        TextField(modifier = Modifier.padding(top = 25.dp),value = "", onValueChange = {}, placeholder = {
            Text(text = "Correo electronico")
        })
        Spacer(modifier = Modifier.height(10.dp))
        TextField(value = "", onValueChange = {}, placeholder = {
            Text(text = "Contraseña")
        })
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 15.dp),
            onClick = { }) {
            Text(text = "INICIAR SESION")
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    GamerMVVMAppTheme {
        GamerMVVMAppTheme(darkTheme = true) {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background

            ) {
                LoginScreen(rememberNavController())
            }
        }
    }
}