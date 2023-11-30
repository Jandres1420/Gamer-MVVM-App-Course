package com.mvvm.gamermvvmapp.presentation.screens.profile_edit

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.presentation.components.DefaultTopBar
import com.mvvm.gamermvvmapp.presentation.screens.profile_edit.components.ProfileEditContent
import com.mvvm.gamermvvmapp.presentation.screens.profile_edit.components.Update
import com.mvvm.gamermvvmapp.presentation.screens.signUp.components.SignUpContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String
){
    Log.d("ProfileEditScreen", "Usuario: $user")
    Scaffold(
        topBar = {
            DefaultTopBar(title = "Editar usuario",
                upAvaible = true,
                navController = navController)
        },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
    Update()


}