package com.mvvm.gamermvvmapp.domain.use_cases.users

import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {
    suspend operator fun invoke(user: User) = repository.create(user)
}