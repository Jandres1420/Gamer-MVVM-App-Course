package com.mvvm.gamermvvmapp.presentation.screens.my_posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.presentation.navigation.DetailsScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyPostsScreen(navController: NavHostController){

    Scaffold(
        topBar = {},
        content = {
            Text(text = "MyPostsScreen")

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(DetailsScreen.NewPost.route)
                }, modifier = Modifier.padding(bottom = 59.dp)
            ) {
                Icon(imageVector = Icons.Default.Add ,
                    contentDescription = "")
            }
        },
        bottomBar = {}
    )
}