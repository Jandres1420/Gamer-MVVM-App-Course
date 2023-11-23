package com.mvvm.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {
    // STATE FORMULARIO, para el usuario y contraseña con los metodos onEmailInput
    var state by mutableStateOf(LogInState())
    private set
    // EMAIL
//    var email: String by  mutableStateOf("")
    var isEmailValid by mutableStateOf(false)
    private set
    var emailErrMsg by mutableStateOf("")
    private set

    // PASSWORD
//    var password:String by mutableStateOf("")
    var isPasswordValid: Boolean by mutableStateOf(false)
        private set
    var passwordErrMsg: String by  mutableStateOf("")
        private set

    // SE ESTA HACIENDO ESTO DEBIDO AL MOMENTO DEL USUARIO PONER UN USUARIO Y CONTRASEÑAS
    // SE DEBE VERIFICAR SI EL ACCESO ES PERMITIDO O DENEGADO
    // se trabajara LOGIM RESPOES
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    //BUTTON
    var isEnabledLoginButton = false

    val currentUser = authUseCases.getCurrentUser()

    //ESTO SERA LO PRIMERO QUE SE EJECUTE CUANDO SE CREE LA CLASE
    init {
        if(currentUser!=null){//SESION INICIADA
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String){
        // copy nos deja cambiar el valor de un parametro en data class
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        // copy nos deja cambiar el valor de un parametro en data class
        state = state.copy(password = password)
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
    //LA RAZÓN POR LA QUE EL LOGIN ES DE UNA MANERA COMPLETAMENTE DIFERTE
    // ES DEBIDO A QUE ES UNA CORRUTINA entonces para eso se usa
    // viewModelScope.launch {  }
    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password )
        loginResponse = result
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
        isEnabledLoginButton = isEmailValid && isPasswordValid

    }
}