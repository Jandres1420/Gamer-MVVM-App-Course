package com.mvvm.gamermvvmapp.presentation.screens.new_post

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mvvm.gamermvvmapp.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.mvvm.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.mvvm.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context) : ViewModel() {

    var state by mutableStateOf(NewPostState())
    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("XBOX", R.drawable.icon_xbox),
        CategoryRadioButton("NINTENDO", R.drawable.icon_nintendo),
        CategoryRadioButton("MOBILE", R.drawable.icon_mobile)
    )

    // FILE
    var file: File? = null

    var resultingActivityHandler = ResultingActivityHandler()

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if(result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }

    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
//        para el contexto en el viewModel no es puede usar LocalContext.current por el @Composable
//        por eso se pasa con la anotacion a la clase en el constructor
        if(result  != null){
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }
    fun onNewPost(){
        Log.d("NewPostViewModel", "name: ${state.name}")
        Log.d("NewPostViewModel", "description: ${state.description}")
        Log.d("NewPostViewModel", "category: ${state.category}")
        Log.d("NewPostViewModel", "image: ${state.image}")
    }
    fun onNameInput(name:String){
        state = state.copy(name = name)
    }
    fun onCategoryInput(category:String){
        state = state.copy(category = category)
    }
    fun onDescriptionInput(description:String){
        state = state.copy(description = description)
    }
    fun onImageInput(image:String){
        state = state.copy(image = image)
    }


}

data class CategoryRadioButton(
    var category: String,
    var image: Int
)