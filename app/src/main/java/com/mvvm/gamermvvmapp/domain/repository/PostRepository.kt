package com.mvvm.gamermvvmapp.domain.repository

import com.mvvm.gamermvvmapp.domain.model.Post
import com.mvvm.gamermvvmapp.domain.model.Response
import java.io.File

interface PostRepository {
    suspend fun create(post: Post, file: File): Response<Boolean>
}