package com.mvvm.gamermvvmapp.domain.use_cases.posts

import com.mvvm.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val repository: PostRepository) {
    operator fun invoke() = repository.getPosts()
}