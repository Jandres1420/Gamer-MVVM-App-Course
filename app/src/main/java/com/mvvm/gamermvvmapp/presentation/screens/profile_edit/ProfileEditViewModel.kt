package com.mvvm.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mvvm.gamermvvmapp.presentation.screens.signUp.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(): ViewModel(){

    //STATE FORM
    var state by mutableStateOf(ProfileEditState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set



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