package com.mvvm.gamermvvmapp.presentation.navigation

// clase sellada muy paraecida a las enumeraciones
sealed class AuthScreen(val route: String) {
    object Login : AuthScreen("login")
    object SignUp : AuthScreen("signup")
    object Profile: AuthScreen("profile")

//    para especificar el parametro toca usar el {noombre de parametro}
    object ProfileEdit: AuthScreen("profile/edit/{user}") {
//     es como pasar por url y se pasa el parametro user
        fun passUser(user: String) = "profile/edit/$user"
    }

}
