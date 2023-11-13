package com.mvvm.gamermvvmapp.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.model.User


interface AuthRepository {

        val currentUser: FirebaseUser?
        // es suspendida ya que no sabemos en que momento nos va a dar la respuesta
        suspend fun login(email:String, password:String): Response<FirebaseUser>

        suspend fun signUp(user: User): Response<FirebaseUser>
        fun logout()
}
