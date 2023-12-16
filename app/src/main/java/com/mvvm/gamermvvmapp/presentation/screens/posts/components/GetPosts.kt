package com.mvvm.gamermvvmapp.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.presentation.components.ProgressBar
import com.mvvm.gamermvvmapp.presentation.screens.posts.PostsViewModel

@Composable
fun GetPosts(viewModel: PostsViewModel = hiltViewModel()) {

    when(val response = viewModel.postResponse){
        Response.Loading -> {
            ProgressBar()
        }
        // la pregunta de porque en elwhen se esta poniendo is Response.Success  es porque es una  data class
        is Response.Success -> {
            // response.data es <List<Post>>
            PostsContent(posts = response.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.Exception?.message?: "Error Desconocido", Toast.LENGTH_LONG).show()
        }

        else -> {}
    }
}