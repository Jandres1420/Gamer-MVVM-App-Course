package com.mvvm.gamermvvmapp.presentation.screens.profile_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mvvm.gamermvvmapp.R
import com.mvvm.gamermvvmapp.presentation.components.DefaultButton
import com.mvvm.gamermvvmapp.presentation.components.DefaultTextField
import com.mvvm.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpViewModel
import com.mvvm.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditContent(navController: NavHostController, viewModel: ProfileEditViewModel = hiltViewModel()){
    // ACÁ SE ESTA USANDO EL PATRON OBSERVADOR YA QUE SE ESTA TRATANDO CON FLUJOS
    //collectAsState(), que es una forma de observar los cambios en el flujo y reflejar automáticamente esos cambios en la interfaz de usuario de Compose.

    Box(
        modifier = Modifier.fillMaxWidth(),

        )  {
        BoxHeader()
        CardFormSignUp(viewModel)
        // acá preguntamos en que estado esta actualmente el flujo



    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFormSignUp(viewModel: ProfileEditViewModel){
    val state = viewModel.state
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        ),
        modifier =  Modifier.padding(start = 30.dp, end = 30.dp, top = 190.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(modifier = Modifier.padding(top = 50.dp, bottom = 0.dp, start = 0.dp, end = 0.dp)
                ,text = "ACTUALIZACIÓN"
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
                value = state.username,
                modifier =  Modifier.padding(top = 25.dp),
                onValueChange ={viewModel.onUsernameInput(it)}
                , label = "Nombre de usuario"
                , icon = Icons.Default.Person
                , keyBoardType =  KeyboardType.Email
                , errorMsg = viewModel.usernameErrMsg
                , validateField = { viewModel.validateUsername()}
            )
            DefaultButton(text = "ACTUALIZAR DATOS",
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
            )

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )  {
            Image(modifier = Modifier.height(130.dp), painter = painterResource(id = R.drawable.user)
                , contentDescription = "Control de XBOX 360")
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewSignUpContent() {
//    GamerMVVMAppTheme {
//        GamerMVVMAppTheme(darkTheme = true) {
//            // A surface container using the 'background' color from the theme
//            Surface(
//                modifier = Modifier.fillMaxSize(),
//                color = MaterialTheme.colorScheme.background
//
//            ) {
//                SignUpContent(rememberNavController())
//            }
//        }
//    }
//}