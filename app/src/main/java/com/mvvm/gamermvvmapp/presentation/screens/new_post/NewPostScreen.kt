package com.mvvm.gamermvvmapp.presentation.screens.new_post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mvvm.gamermvvmapp.presentation.components.DefaultButton
import com.mvvm.gamermvvmapp.presentation.components.DefaultTopBar
import com.mvvm.gamermvvmapp.presentation.navigation.DetailsScreen
import com.mvvm.gamermvvmapp.presentation.screens.new_post.components.NewPostContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NewPostScreen(navController: NavHostController){
    Scaffold(
        topBar = {
                 DefaultTopBar(title = "Nuevo Post", upAvaible = true, navController = navController)
        },
        content = {
            NewPostContent()
        },
        bottomBar = {
            DefaultButton(text = "PUBLICAR",
                onClick = {},
                modifier = Modifier.fillMaxWidth())
        }

    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun getNewPostScreen(){
    NewPostScreen(rememberNavController())
}