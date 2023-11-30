package com.mvvm.gamermvvmapp.presentation.screens.profile_edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.use_cases.users.UsersUseCases
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


// el parametro del constructor es para pasar de una clase a otra usando viewModel, desde Navigaion {user}
@HiltViewModel
class ProfileEditViewModel @Inject constructor
    (
    private val savedStateHandle: SavedStateHandle,
    private val userUsersUseCases: UsersUseCases
) : ViewModel() {

    //STATE FORM
    var state by mutableStateOf(ProfileEditState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set

    //asi recuperamos de la ruta
    val userString = savedStateHandle.get<String>("user")
    val user = User.fromJson(userString!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var imageUri by mutableStateOf<Uri?>(null)
    var hasImage by mutableStateOf(false)


    init {
        state = state.copy(username = user.username)
    }

    fun onGalleryResult(uri:Uri?){
        hasImage= uri != null // true o false
        imageUri = uri
    }

    fun onResult(result: Boolean){
        hasImage = result
    }
    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = userUsersUseCases.update(user)
        updateResponse = result
    }

    fun onUpdate(){
        val myUser = User(
            id = user.id,
            username = state.username,
            image = ""
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