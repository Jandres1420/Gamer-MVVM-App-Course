package com.mvvm.gamermvvmapp.domain.repository

import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File


// peticiones a firestore
interface UsersRepository {


    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>

    suspend fun saveImage(file: File): Response<String>

    // De retorno normal seria User pero como lo queremos en tiempo real usaremos Flow
//    y no se usa suspend porque no es una corrutina simple ya que firestore la brinda así
    fun getUserById(id : String): Flow<User>


}