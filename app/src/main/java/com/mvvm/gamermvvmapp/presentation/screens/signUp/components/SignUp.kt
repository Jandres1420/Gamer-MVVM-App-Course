package com.mvvm.gamermvvmapp.presentation.screens.signUp.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.presentation.components.ProgressBar
import com.mvvm.gamermvvmapp.presentation.navigation.AppScreen
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpViewModel

@Composable
fun SignUp(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {
    when(val signUpResponse = viewModel.signUpRespone){
        Response.Loading -> {
           ProgressBar()
        }
        // se pone en este caso del when is porque Response.Success es un  data class
        is Response.Success ->{
            LaunchedEffect(Unit){
                // se esta diciendo que cada vez que un usuario sea creado correctamente y su estado sea SUCCESS cree el usuario en la coleccion
                viewModel.createUser()
                navController.popBackStack(AppScreen.Login.route, true)
                navController.navigate(route = AppScreen.Profile.route)
            }
        }
        is Response.Failure ->{
            Toast.makeText(LocalContext.current, signUpResponse.Exception?.message?: "Error Desconocido", Toast.LENGTH_LONG )
        }

        else -> {}
    }
}