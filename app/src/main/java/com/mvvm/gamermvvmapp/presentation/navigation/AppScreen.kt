package com.mvvm.gamermvvmapp.presentation.navigation
// clase sellada muy paraecida a las enumeraciones
sealed class AppScreen(val route:String){
    object Login: AppScreen("login")
    object SignUp: AppScreen("signup")
    object Profile: AppScreen("profile")

}
