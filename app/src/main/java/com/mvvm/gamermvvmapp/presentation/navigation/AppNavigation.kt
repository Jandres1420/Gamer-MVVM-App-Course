package com.mvvm.gamermvvmapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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

        composable(route = AppScreen.ProfileEdit.route){
            ProfileEditScreen(navController)
        }
    }
}