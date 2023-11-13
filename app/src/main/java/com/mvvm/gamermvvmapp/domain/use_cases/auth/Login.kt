package com.mvvm.gamermvvmapp.domain.use_cases.auth

import com.mvvm.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.mvvm.gamermvvmapp.domain.repository.AuthRepository

class Login constructor(private val repository:AuthRepository ) {


    // lo mismo no hay que tener injecciones regadas para eso sirve el di
//    val repository: AuthRepository = AuthRepositoryImpl()
    //IMPORRTANTE A PENAS LA CLASE LOGIN SEA LLAMADA INSTANTANEAMER SE VA EJECUTAR INVOKE
    suspend operator fun invoke(email:String, password: String) = repository.login(email, password)
}