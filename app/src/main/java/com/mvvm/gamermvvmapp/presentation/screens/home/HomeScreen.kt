package com.mvvm.gamermvvmapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mvvm.gamermvvmapp.presentation.navigation.HomeBottomBarNavGraph
import com.mvvm.gamermvvmapp.presentation.navigation.HomeBottomBarScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
// el rememberNavController(), asegurar que el estado del controlador de navegación se
// preserve correctamente a través de recomposiciones, manteniendo la coherencia de la navegación a lo largo del ciclo de vida de la aplicación.
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        HomeBottomBarNavGraph(navController = navController)
    }
}

@Composable
fun RowScope.AddItem(
    screen: HomeBottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
                Text(text = screen.title)
        },
        icon = {
               Icon(imageVector = screen.icon,
                   contentDescription = "Navigation icon" )
        },
        selected = currentDestination?.hierarchy?.any() {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Composable
fun BottomBar(navController: NavHostController){
    // lista de pantallas que se quieren mostrar
    val screen = listOf(
        HomeBottomBarScreen.Posts,
        HomeBottomBarScreen.MyPosts,
        HomeBottomBarScreen.Profile
    )
    //lo demás son configuraciones
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val bottomBarDestination = screen.any{
        it.route == currentDestination?.route
    }
    if(bottomBarDestination){
        BottomNavigation {
            screen.forEach{screen ->
                AddItem(screen = screen,
                    currentDestination = currentDestination ,
                    navController = navController )
            }
        }
    }
}