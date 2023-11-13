package com.mvvm.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {
    //EMAIL
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")
    //PASSWORD
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    // SE ESTA HACIENDO ESTO DEBIDO AL MOMENTO DEL USUARIO PONER UN USUARIO Y CONTRASEÑAS
    // SE DEBE VERIFICAR SI EL ACCESO ES PERMITIDO O DENEGADO
    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow:StateFlow<Response<FirebaseUser>?> = _loginFlow

    //BUTTON
    var isEnabledLoginButton = false

    fun validateEmail(){
        // basicamente lo que hace este if es mirar si es un correo valido
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrMsg.value = ""
        }else{
            isEmailValid.value = false
            emailErrMsg.value = "El email no es valido"
        }
        enabledLogInButton()
    }
    //LA RAZÓN POR LA QUE EL LOGIN ES DE UNA MANERA COMPLETAMENTE DIFERTE
    // ES DEBIDO A QUE ES UNA CORRUTINA entonces para eso se usa
    // viewModelScope.launch {  }
    fun login() = viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCases.login(email.value, password.value )
        _loginFlow.value = result
    }

    fun validatePassword(){
        // basicamente lo que hace este if es mirar si es un correo valido
        if(password.value.length >=6){
            isPasswordValid.value = true
            passwordErrMsg.value = ""
        }else{
            isPasswordValid.value = false
            passwordErrMsg.value = "Al menos 6 caracteres"
        }
        enabledLogInButton()
    }

    fun enabledLogInButton(){
        isEnabledLoginButton = isEmailValid.value && isPasswordValid.value

    }
}