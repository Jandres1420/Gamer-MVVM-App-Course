package com.mvvm.gamermvvmapp.domain.use_cases.auth

import com.mvvm.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class LogOut @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.logout()
}