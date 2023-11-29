package com.mvvm.gamermvvmapp.presentation.navigation
// clase sellada muy paraecida a las enumeraciones
sealed class AppScreen(val route:String){
    object Login: AppScreen("login")
    object SignUp: AppScreen("signup")
    object Profile: AppScreen("profile")

    // para especificar el parametro toca usar el {noombre de parametro}
    object ProfileEdit: AppScreen("profile/edit/{user}") {
        // es como pasar por url y se pasa el parametro user
        fun passUser(user: String) = "profile/edit/$user"
    }

}
