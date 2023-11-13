package com.mvvm.gamermvvmapp.presentation.screens.signUp

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    //EMAIL
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")
    //PASSWORD
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    //USERNAME
    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrMsg: MutableState<String> = mutableStateOf("")

    // CONFIRMAR CONTERASEÑA
    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    //BUTTON
    var isEnabledLoginButton = false

    private val _signUpFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signUpFlow: StateFlow<Response<FirebaseUser>?> = _signUpFlow


    fun onSignUp(){
        val user = User(
            username = username.value,
            email = email.value,
            password = password.value
        )
        signUp(user)
    }
    // ViewModelScoper porque se estan usando corrutinas
    fun signUp(user: User) = viewModelScope.launch{
        _signUpFlow.value = Response.Loading
        val result = authUseCases.signUp(user)
        _signUpFlow.value = result
    }
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

    fun validateUsername(){
        if(username.value.length >= 5){
            isUsernameValid.value = true
            usernameErrMsg.value = ""
        }else{
            isUsernameValid.value = false
            usernameErrMsg.value = "Al menos 5 caracteres"
        }
        enabledLogInButton()
    }

    fun validateConfirmPassword(){
        if(password.value == confirmPassword.value){
            isConfirmPasswordValid.value = true
            confirmPasswordErrMsg.value = ""
        }else{
            isConfirmPasswordValid.value = false
            confirmPasswordErrMsg.value = "Las contraseñas no coinciden"
        }
        enabledLogInButton()
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
        isEnabledLoginButton = isEmailValid.value && isPasswordValid.value && isUsernameValid.value
                && isConfirmPasswordValid.value

    }


}