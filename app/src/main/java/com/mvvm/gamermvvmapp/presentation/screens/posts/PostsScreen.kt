package com.mvvm.gamermvvmapp.presentation.screens.posts

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.presentation.screens.posts.components.GetPosts
import com.mvvm.gamermvvmapp.presentation.ui.theme.Darkgray700

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostsScreen(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()){
    
    Scaffold(
        topBar = {},
        content = {
            GetPosts()
            
        },
        bottomBar = {}
    ) 
}