package com.mvvm.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mvvm.gamermvvmapp.presentation.ui.theme.Red700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//        TextField es EditText, el parametro value sera la variable que va ha estar cambiando conforme se escriba, onValueChange el estado, place holder es como el preview al que se debe ingresar
//        OutlinedTextField es EditText, diferente diseño y en vez de placeholder se usa label
fun DefaultTextField(value: String,
    modifier: Modifier,
    onValueChange: (value:String) -> Unit,
    label: String,
    icon: ImageVector,
    keyBoardType: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false,
    errorMsg: String = "",
    validateField: () -> Unit)
{
    Column() {
        OutlinedTextField(modifier = modifier,
            value = value,
            onValueChange = {
                    myValue -> onValueChange(myValue)
                    validateField() }
            , label = {
                Text(label  )
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
            // leadingIcon es para que comience el icono desde la izquierda , trailinIcon comienza desde la derecha
            leadingIcon = {
                Icon(
                    imageVector = icon
                    , contentDescription = "",
                    tint = Color.White
                )
            },
            visualTransformation = if(hideText) PasswordVisualTransformation() else VisualTransformation.None
        )
        Text(text = errorMsg, modifier.padding(top = 5.dp), fontSize = 11.sp , color = Red700  )
    }

}