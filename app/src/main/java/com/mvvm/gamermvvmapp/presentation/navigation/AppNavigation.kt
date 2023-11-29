package com.mvvm.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mvvm.gamermvvmapp.presentation.screens.login.LoginScreen
import com.mvvm.gamermvvmapp.presentation.screens.profile.ProfileScreen
import com.mvvm.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    )   {
        composable(route = AppScreen.Login.route){
            LoginScreen(navController)
        }
        composable(route = AppScreen.SignUp.route){
            SignUpScreen(navController)
        }
        composable(route = AppScreen.Profile.route){
            ProfileScreen(navController)
        }

        composable(
            route = AppScreen.ProfileEdit.route,
            // el navArgument() tiene que coincidir con el parametro puesto en appScreen
            arguments = listOf(navArgument("user"){
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