package com.mvvm.gamermvvmapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.R
import com.mvvm.gamermvvmapp.presentation.components.DefaultButton
import com.mvvm.gamermvvmapp.presentation.components.DefaultTextField
import com.mvvm.gamermvvmapp.presentation.screens.login.LogInViewModel
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//IMPORTANTISIMO TENER TODA LA PARTE UI ECHA ANTES DE USAR VIEWMODEL
// LA INSTANCIA LOGINVIWMODEL JAMAS FUE DECLARADA GRACIAS A LA INJECCION = hiltViewModel())
fun LogInContent(navController: NavHostController, viewModel: LogInViewModel = hiltViewModel()){

    // ESTA ES LA VIA PARA PODER CONECTARSE CON EL VIEWMODEL
//    val loginFlow = viewModel.loginFlow.collectAsState()
//    para modificar que todos los componentes tenganun atributo se usa modifier dentro del Column
    Box(
//        fil max width cubrira todo el ancho, wrapcontent hara que todo se ponga con respecto al tamaño que tenga
        modifier = Modifier.fillMaxWidth(),

    )  {
        BoxHeader()
        // modifier hara varios cambios en diseño con respecto al ui como las properties xml
        // Text(modifier = Modifier.padding(top = 30.dp),text = "LOGIN") DE ESTA MANERA SOLO ABRIA UNA DISTANCIA HACIA ARRIBA DE 30DP, si no se pone top tendira separación hacia todos los lados
        cardForm(viewModel)

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardForm(viewModel: LogInViewModel){
    var state = viewModel.state
    Card(
        colors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.tertiary,
    ),
        modifier =  Modifier.padding(start = 30.dp, end = 30.dp, top = 170.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(modifier = Modifier.padding(top = 30.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
                ,text = "LOGIN"
                , fontSize = 18.sp
                , fontWeight = FontWeight.Bold
                , color = Color.White
            )
            // otra forma de hacer un padding es con Spacer(modifier = Modifier.height(10.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Por favor inicia sesion para continuar"
                , fontSize = 12.sp,
                color = Color.Gray
            )

            DefaultTextField(
                value = state.email,
                modifier =  Modifier.padding(top = 25.dp),
                onValueChange ={viewModel.onEmailInput(it)}
                , label = "Correo electronico"
                , icon = Icons.Default.Email
                , keyBoardType =  KeyboardType.Email
                , errorMsg = viewModel.emailErrMsg
                , validateField = {viewModel.validateEmail()})
            DefaultTextField(value = state.password,
                modifier =  Modifier.padding(top = 10.dp),
                onValueChange ={viewModel.onPasswordInput(it)}
                , label = "Contraseña"
                , icon = Icons.Default.Lock
                , hideText = true,
                errorMsg = viewModel.passwordErrMsg
                , validateField = {viewModel.validatePassword()})
            DefaultButton(text = "INICIAR SESION", onClick = {
                viewModel.login()

            },enabled =  viewModel.isEnabledLoginButton,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 70.dp))


        }
    }

}

@Composable
fun BoxHeader(){
    Box(
        modifier = Modifier
            .height(235.dp)
            .background(Red500)
            .fillMaxWidth(),
    ){
        Column(
//        fil max width cubrira todo el ancho, wrapcontent hara que todo se ponga con respecto al tamaño que tenga
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        )  {
        Image(modifier = Modifier.height(130.dp), painter = painterResource(id = R.drawable.control)
            , contentDescription = "Control de XBOX 360")
            Text(text = "FIREBASE MVVM", modifier = Modifier.padding(bottom = 20.dp))
        }
    }
}

