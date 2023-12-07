package com.mvvm.gamermvvmapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeBottomBarScreen(
    val route: String,
    var title: String,
    val icon: ImageVector
){
    object Posts: HomeBottomBarScreen(route = "posts",
        title = "Publicaciones",
        icon = Icons.Default.List)
    object MyPosts: HomeBottomBarScreen(route = "my_posts",
        title = "Mis Posts",
        icon = Icons.Outlined.List)
    object Profile: HomeBottomBarScreen(route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person)
}
