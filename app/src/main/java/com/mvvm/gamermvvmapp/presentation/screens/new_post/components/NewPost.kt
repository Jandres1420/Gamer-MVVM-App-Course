package com.mvvm.gamermvvmapp.presentation.screens.new_post.components

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
import com.mvvm.gamermvvmapp.presentation.screens.login.LogInViewModel
import com.mvvm.gamermvvmapp.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPost(viewModel: NewPostViewModel = hiltViewModel()){
    when(val response = viewModel.createPostResponse){
        Response.Loading -> {
            ProgressBar()
        }

        // la pregunta de porque en elwhen se esta poniendo is Response.Success  es porque es una  data class
        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(LocalContext.current,  "La publicacion se ha creado correctamente", Toast.LENGTH_LONG).show()
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.Exception?.message?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}