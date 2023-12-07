package com.mvvm.gamermvvmapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mvvm.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileEdit.route
    ){
        composable(
            route = DetailsScreen.ProfileEdit.route,
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

sealed class DetailsScreen( val route: String){
    //    para especificar el parametro toca usar el {noombre de parametro}
    object ProfileEdit: DetailsScreen("profile/edit/{user}") {
        //     es como pasar por url y se pasa el parametro user
        fun passUser(user: String) = "profile/edit/$user"
    }

}