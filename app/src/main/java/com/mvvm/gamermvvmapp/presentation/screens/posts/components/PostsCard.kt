package com.mvvm.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mvvm.gamermvvmapp.domain.model.Post

@Composable
fun PostsCard(post: Post) {
    // sombra  = elevation
    Card(
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column {
//             La imagen se expanda al ancho del recuadro contentScale = ContentScale.Crop
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                text = post.name
            )
            Text(
                text = post.idUser,
                fontSize = 13.sp,
                color = Color.Black
            )
            Text(
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )
        }
    }
}