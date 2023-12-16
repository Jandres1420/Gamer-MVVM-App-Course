package com.mvvm.gamermvvmapp.domain.repository

import com.mvvm.gamermvvmapp.domain.model.Post
import com.mvvm.gamermvvmapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {
    // Flow es para cambiar el estado de la data por eso no es suspendida de manera asincrona
    fun getPosts(): Flow<Response<List<Post>>>
    suspend fun create(post: Post, file: File): Response<Boolean>
}