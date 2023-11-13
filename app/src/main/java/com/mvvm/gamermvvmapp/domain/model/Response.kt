package com.mvvm.gamermvvmapp.domain.model

import java.lang.Exception

sealed class Response<out T>{
    // IMPORTANTE TODO LO QUE ESTA DANDO ESTA CLASE SON ESTADOS , cargando, fallecido , etc
    object Loading: Response<Nothing>()
    data class Success<out T>(val data: T): Response<T>()
    data class Failure<out T>(val Exception: Exception?): Response<T>()
}
