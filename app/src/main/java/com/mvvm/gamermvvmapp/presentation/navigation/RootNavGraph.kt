package com.mvvm.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mvvm.gamermvvmapp.presentation.screens.home.HomeScreen
import com.mvvm.gamermvvmapp.presentation.screens.login.LoginScreen
import com.mvvm.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.mvvm.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpScreen

// de momento es una navegación simple, porque pasando de una pantalla a otra, pero al implementar un navigationbar la navegación cambia y toca hacer una configuración
// diferente, una grafo
@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {

        // para la parte del grafo de autenticacion
        authNavGraph(navController = navController)

        // la parte del grafo de home , NAVBAR, es un composable ya que es un navhost, dentro de un
        // navhost por lo que toca crear una pantalla que en este caso es HomeScreen()
        composable(route = Graph.HOME){
            HomeScreen()
        }

    }
}

