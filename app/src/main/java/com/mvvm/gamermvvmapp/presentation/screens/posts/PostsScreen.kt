package com.mvvm.gamermvvmapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController){
    
    Scaffold(
        topBar = {},
        content = {
            Text(text = "PostScreen")
            
        },
        bottomBar = {}
    ) 
}