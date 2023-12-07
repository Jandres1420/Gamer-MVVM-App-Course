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

        // la parte del grafo de home , NAVBAR
        composable(route = RootScreen.Home.route){
            HomeScreen()
        }



        composable(
            route = AuthScreen.ProfileEdit.route,
            // el navArgument() tiene que coincidir con el parametro puesto en appScreen
            arguments = listOf(navArgument("user") {
                type = NavType.StringType
            })
        )
        {
            // el it va a buscar dentro de la pantalla el argumento que se esta intentando pasar en este caso es String user, que tambien esta como parametro en profileEditScreen
            // para evitar el nulo usamos let{ } , NULL SAFETY
            it.arguments?.getString("user")?.let {
                ProfileEditScreen(navController, user = it)
            }

        }
    }
}