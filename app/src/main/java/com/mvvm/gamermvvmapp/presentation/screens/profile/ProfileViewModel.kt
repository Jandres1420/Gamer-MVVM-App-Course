package com.mvvm.gamermvvmapp.presentation.screens.profile
// para poder trabajar de meutablestateo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mvvm.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases,
                                           private val usersUseCases: UsersUseCases):ViewModel() {
    // es lo mismo pero con mejores praticas
//    var userData: MutableState<User> = mutableStateOf(User())
    // private set, sera una privada de java
    var userData by mutableStateOf(User())
        private set
    var currentUser = authUseCases.getCurrentUser()
    init {
        getUserById()
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(currentUser!!.uid).collect(){ data ->
            userData = data
        }
    }
    fun logOut(){
        authUseCases.logOut()
    }
}