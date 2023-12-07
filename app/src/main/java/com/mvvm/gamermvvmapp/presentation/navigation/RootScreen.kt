package com.mvvm.gamermvvmapp.presentation.navigation

sealed class RootScreen(val route: String){
    object Home: RootScreen("home")
}
