package com.mvvm.gamermvvmapp.presentation.screens.new_post.components

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mvvm.gamermvvmapp.R
import com.mvvm.gamermvvmapp.presentation.components.DefaultTextField
import com.mvvm.gamermvvmapp.presentation.ui.theme.Darkgray700
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red500


data class CategoryRadioButton(var category: String, var image: Int){}
@Composable
fun NewPostContent() {
    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
    CategoryRadioButton("PS4", R.drawable.icon_ps4),
    CategoryRadioButton("XBOX", R.drawable.icon_xbox),
    CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOBILE", R.drawable.icon_mobile))
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Darkgray700)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            // para agregar el scroll
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .height(180.dp)
                    .background(Red500)
                    .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier
                            .height(130.dp),
                        painter = painterResource(id = R.drawable.add_image),
                        contentDescription = "Control de XBOX 360"
                    )
                    Text(
                        text = "SELECCIONA UNA IMAGEN",
                        modifier = Modifier.padding(bottom = 30.dp),
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            DefaultTextField(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp),
                onValueChange ={}
                , label = "Nombre del juego"
                , icon = Icons.Default.Face
                , errorMsg = ""
                , validateField = {})
            DefaultTextField(
                value = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, start = 20.dp, end = 20.dp),
                onValueChange ={}
                , label = "Descripcion"
                , icon = Icons.Default.List
                , errorMsg = ""
                , validateField = {})
            Text(
                modifier = Modifier.padding(vertical = 15.dp),
                text = "CATEGORIAS",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            radioOptions.forEach{option ->
                Row (modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .selectable(selected = false, onClick = {}),
                    verticalAlignment = Alignment.CenterVertically){
                    RadioButton(
                        selected = false ,
                        onClick = {})
                    Row() {
                        Text(
                            text = option.category,
                            modifier = Modifier
                                .width(110.dp)
                                .padding(12.dp),
                            color = Color.White
                        )
                        Image(
                            modifier = Modifier
                                .height(40.dp)
                                .padding(8.dp),
                            painter = painterResource(id = option.image),
                            contentDescription = "Control de XBOX 360"
                        )
                    }

                }
            }
        }

    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNewPostContent() {
    NewPostContent()
}