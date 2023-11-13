package com.mvvm.gamermvvmapp.presentation.screens.signUp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mvvm.gamermvvmapp.presentation.components.DefaultTopBar
import com.mvvm.gamermvvmapp.presentation.screens.login.components.LogInContent
import com.mvvm.gamermvvmapp.presentation.screens.signUp.components.SignUpContent
import com.mvvm.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            DefaultTopBar(title = "Nuevo usuario",
            upAvaible = true,
            navController = navController)
        },
        content = {
            SignUpContent(navController)
                      },
        bottomBar = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSignUpScreen(){ GamerMVVMAppTheme {
    GamerMVVMAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background

        ) {
            SignUpScreen(rememberNavController())
        }
    }
}

}