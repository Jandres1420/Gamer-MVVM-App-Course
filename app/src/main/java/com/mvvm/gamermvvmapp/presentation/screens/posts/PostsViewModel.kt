package com.mvvm.gamermvvmapp.presentation.screens.posts

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mvvm.gamermvvmapp.domain.model.Post
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.use_cases.posts.PostsUseCases
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsUseCases: PostsUseCases
) : ViewModel() {

    var postResponse by mutableStateOf<Response<List<Post>>?>(null)

    init{
        getPosts()
    }
    // toca recordar que es tiempo real por lo que no se usa postResponse = result y que es esta manejando con FLow
    fun getPosts() = viewModelScope.launch {
        postResponse = Response.Loading
        // collect va a traer los datos
        postsUseCases.getPosts().collect{response ->
            postResponse = response
        }
    }
}