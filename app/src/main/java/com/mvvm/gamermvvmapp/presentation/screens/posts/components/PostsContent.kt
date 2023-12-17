package com.mvvm.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mvvm.gamermvvmapp.domain.model.Post
import com.mvvm.gamermvvmapp.presentation.ui.theme.Darkgray700

@Composable
fun PostsContent(
    posts: List<Post>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Darkgray700)
    ) {
        items(items = posts){post ->
            PostsCard(post = post)
        }
    }
}