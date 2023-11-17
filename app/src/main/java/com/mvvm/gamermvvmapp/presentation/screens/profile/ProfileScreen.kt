package com.mvvm.gamermvvmapp.presentation.screens.profile

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.presentation.components.DefaultButton
import com.mvvm.gamermvvmapp.presentation.navigation.AppScreen
import com.mvvm.gamermvvmapp.presentation.screens.profile.components.ProfileContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()){
    Scaffold(
        topBar = {},
        content = {
            ProfileContent(navController)
        },
        bottomBar = {}
    )
}
