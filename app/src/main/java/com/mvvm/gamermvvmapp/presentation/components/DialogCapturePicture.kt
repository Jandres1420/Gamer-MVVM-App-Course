package com.mvvm.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Dialog es como un popup el cual nos dira si tomara la foto desde la camara o de la galeria
@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit,
) {
    if (status.value) {
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            onDismissRequest = {
                // esta linea hara que cuando se oprima afuera del dialog se oculte
                status.value = false

            },
            backgroundColor = Color.White,
            title = {
                Text(
                    text = "Selecciona una opcion",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            },
            buttons = {
                // poner una fila que contendra los dos botones
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp),
                    //esto hara que los botones esten simetricos y bien distribuidos
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            pickImage()
                        }
                    ) {
                        Text(text = "Galeria")
                    }
                    Button(modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            takePhoto()

                        }
                    ) {
                        Text(text = "camera")
                    }
                }

            })
    }
}