package com.mvvm.gamermvvmapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases):ViewModel() {

    fun logOut(){
        authUseCases.logOut()
    }
}