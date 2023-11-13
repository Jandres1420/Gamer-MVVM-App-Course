package com.mvvm.gamermvvmapp.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mvvm.gamermvvmapp.domain.model.Response


interface AuthRepository {

        val currentUser: FirebaseUser?
        suspend fun login(email:String, password:String): Response<FirebaseUser>

}
