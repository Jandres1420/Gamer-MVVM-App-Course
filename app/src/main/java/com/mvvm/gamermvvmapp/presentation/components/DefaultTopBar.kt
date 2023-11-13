package com.mvvm.gamermvvmapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red500
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(title: String, upAvaible: Boolean = false, navController: NavHostController? = null){
    TopAppBar(
        title = {
            Text(text = title,
            fontSize = 19.sp)
        },
        backgroundColor = Red500,
        modifier = Modifier.fillMaxWidth().background(Red700),
        navigationIcon = {
            if((upAvaible)){
                IconButton(onClick = { navController?.popBackStack()}) {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }
        }
    )
}