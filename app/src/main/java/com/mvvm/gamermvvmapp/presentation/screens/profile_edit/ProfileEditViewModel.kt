package com.mvvm.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


// el parametro del constructor es para pasar de una clase a otra usando viewModel, desde Navigaion {user}
@HiltViewModel
class ProfileEditViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): ViewModel(){

    //STATE FORM
    var state by mutableStateOf(ProfileEditState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set

    //asi recuperamos de la ruta
    val userString = savedStateHandle.get<String>("user")
    val user = User.fromJson(userString!!)

    init{
        state = state.copy(username = user.username)
    }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun validateUsername(){
        if(state.username.length >= 5){
            usernameErrMsg = ""
        }else{
            usernameErrMsg = "Al menos 5 caracteres"
        }
    }
}