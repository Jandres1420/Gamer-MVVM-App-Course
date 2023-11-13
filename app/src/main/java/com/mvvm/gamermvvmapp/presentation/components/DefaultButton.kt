package com.mvvm.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red500
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red700

@Composable
fun DefaultButton(text: String,
                  onClick: () -> Unit,
                  color: Color = Red500,
                  icon: ImageVector = Icons.Default.ArrowForward,
                  modifier: Modifier,
                  errorMsg: String = "",
                  enabled: Boolean = true){
    Column() {
        Button(
            modifier = modifier,
            onClick = { onClick()},
            enabled = enabled,
        ) {
            Icon(imageVector = icon,
                contentDescription = "")
            Text(text = "$text")
        }
        Text(modifier = Modifier.padding(top = 5.dp),
            text = errorMsg,
            fontSize =  11.sp,
            color = Red700
        )
    }

}