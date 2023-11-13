package com.mvvm.gamermvvmapp.domain.use_cases.auth

import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class SignUp @Inject constructor(private val  repository: AuthRepository) {
    suspend operator fun invoke(user: User) = repository.signUp(user)
}