package com.mvvm.gamermvvmapp.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.presentation.components.ProgressBar
import com.mvvm.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel

@Composable
fun Update(viewModel: ProfileEditViewModel = hiltViewModel()){

    when(val updateResponse = viewModel.updateResponse){
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Loading -> {
            Toast.makeText(LocalContext.current, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()

        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, updateResponse.Exception?.message?: "Error desconocido", Toast.LENGTH_SHORT).show()
        }

        else -> {}
    }
}