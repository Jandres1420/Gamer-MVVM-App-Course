package com.mvvm.gamermvvmapp.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.presentation.components.ProgressBar
import com.mvvm.gamermvvmapp.presentation.navigation.AuthScreen
import com.mvvm.gamermvvmapp.presentation.navigation.Graph
import com.mvvm.gamermvvmapp.presentation.navigation.RootScreen
import com.mvvm.gamermvvmapp.presentation.screens.login.LogInViewModel

@Composable
fun Login(navController: NavHostController, viewModel: LogInViewModel = hiltViewModel()){
    when(val logInResponse = viewModel.loginResponse){
        Response.Loading -> {
            ProgressBar()
        }

        // la pregunta de porque en elwhen se esta poniendo is Response.Success  es porque es una  data class
        is Response.Success -> {
            // funcion de corrutina pasandole una función lambda, efecto secundario, SE USA DE ESTA MANERA EL NAVIGATE YA QUE SE ESTA USANDO UN ESTADO
            /// EN ESTE CASO SOLO VA A NAVEGAR A LA PANTALLA DE PROFILE CUANDO EL ESTA SEA EXITOSO
            LaunchedEffect(Unit){
                // de esta manera vamos a la pantalle profile, pero la idea es que no le pueda da rpara atras por lo que se usa el metodo popUpTo
                navController.navigate(route = RootScreen.Home.route){
                    // ya no se elimina la pantalla anterior, si no todo el grafo de auth
                    popUpTo(Graph.AUTHENTICATION){ inclusive = true}
                }
            }
            Toast.makeText(LocalContext.current, "Usuario Logeado", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, logInResponse.Exception?.message?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}