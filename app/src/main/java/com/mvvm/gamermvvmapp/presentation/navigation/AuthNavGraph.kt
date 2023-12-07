package com.mvvm.gamermvvmapp.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mvvm.gamermvvmapp.presentation.screens.login.LoginScreen
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    // NAVIGATION PORQUE ESTO ES LINEAL
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = AuthScreen.SignUp.route) {
            SignUpScreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object SignUp : AuthScreen("signup")


}
