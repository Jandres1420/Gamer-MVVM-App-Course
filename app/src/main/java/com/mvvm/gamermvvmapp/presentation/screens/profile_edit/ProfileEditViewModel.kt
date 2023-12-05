package com.mvvm.gamermvvmapp.presentation.screens.profile_edit

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.use_cases.users.UsersUseCases
import com.mvvm.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.mvvm.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


// el parametro del constructor es para pasar de una clase a otra usando viewModel, desde Navigaion {user}
@HiltViewModel
class ProfileEditViewModel @Inject constructor
    (
    private val savedStateHandle: SavedStateHandle,
    private val userUsersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(ProfileEditState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set

    //asi recuperamos de la ruta (ARGUMENTS)
    //ARGUMENTS
    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    //RESPONSE
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    // RESPONSE IMAGE
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set



    var resultingActivityHandler = ResultingActivityHandler()

    // FILE
    var file: File? = null

    init {
        // dentro del estado del formulario tambien esta image que se llama igual que firestore
        state = state.copy(username = user.username, image = user.image)

    }

    fun saveImage() = viewModelScope.launch {
        if(file != null){
            saveImageResponse = Response.Loading
            val result = userUsersUseCases.saveImage(file!!)
            saveImageResponse = result
        }
    }
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


    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = userUsersUseCases.update(user)
        updateResponse = result
    }

    fun onUpdate(url: String) {
        val myUser = User(
            id = user.id,
            username = state.username,
            image = url
        )
        update(myUser)
    }

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun validateUsername() {
        if (state.username.length >= 5) {
            usernameErrMsg = ""
        } else {
            usernameErrMsg = "Al menos 5 caracteres"
        }
    }
}