package com.mvvm.gamermvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.mvvm.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.mvvm.gamermvvmapp.domain.repository.AuthRepository
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mvvm.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.mvvm.gamermvvmapp.domain.use_cases.auth.LogOut
import com.mvvm.gamermvvmapp.domain.use_cases.auth.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        // ac
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logOut = LogOut(repository)
    )
}