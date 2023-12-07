package com.mvvm.gamermvvmapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mvvm.gamermvvmapp.presentation.navigation.HomeBottomBarNavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
// el rememberNavController(), asegurar que el estado del controlador de navegación se
// preserve correctamente a través de recomposiciones, manteniendo la coherencia de la navegación a lo largo del ciclo de vida de la aplicación.
fun HomeScreen(navController: NavHostController = rememberNavController()){
    Scaffold() {
        HomeBottomBarNavGraph(navController = navController)
    }
}