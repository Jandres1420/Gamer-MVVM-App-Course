package com.mvvm.gamermvvmapp.presentation.screens.signUp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import com.mvvm.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases): ViewModel() {

    //STATE FORM
    var state by mutableStateOf(SignUpState())
        private set
    //EMAIL
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by  mutableStateOf("")
        private set
    //PASSWORD
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    //USERNAME
    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    // CONFIRMAR CONTERASEÑA
    var isConfirmPasswordValid by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
        private set

    //BUTTON
    var isEnabledLoginButton = false

    var signUpRespone by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun onSignUp(){
        user.username = state.username
        user.email =  state.email
        user.password = state.password

        signUp(user)
    }
    // ViewModelScoper porque se estan usando corrutinas
    // PORQUE ES MUY IMPORTANTE YA QUE NO SABEMOS EXACTAMENTE CUANDO SE VA HACER ESTE LLAMADO
    // Este metodo hara la creacion de usuario en fireAuthentication
    fun signUp(user: User) = viewModelScope.launch{
        signUpRespone = Response.Loading
        val result = authUseCases.signUp(user)
        signUpRespone = result
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }
    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }
    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }
    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword = confirmPassword)
    }
    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }
    fun createUser(user: User) = viewModelScope.launch {

    }
    fun validateEmail(){
        // basicamente lo que hace este if es mirar si es un correo valido
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrMsg = ""
        }else{
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }
        enabledLogInButton()
    }

    fun validateUsername(){
        if(state.username.length >= 5){
            isUsernameValid = true
            usernameErrMsg = ""
        }else{
            isUsernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }
        enabledLogInButton()
    }

    fun validateConfirmPassword(){
        if(state.password == state.confirmPassword){
            isConfirmPasswordValid = true
            confirmPasswordErrMsg = ""
        }else{
            isConfirmPasswordValid = false
            confirmPasswordErrMsg = "Las contraseñas no coinciden"
        }
        enabledLogInButton()
    }

    fun validatePassword(){
        // basicamente lo que hace este if es mirar si es un correo valido
        if(state.password.length >=6){
            isPasswordValid = true
            passwordErrMsg = ""
        }else{
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }
        enabledLogInButton()
    }

    fun enabledLogInButton(){
        isEnabledLoginButton = isEmailValid
                && isPasswordValid
                && isUsernameValid
                && isConfirmPasswordValid

    }


}