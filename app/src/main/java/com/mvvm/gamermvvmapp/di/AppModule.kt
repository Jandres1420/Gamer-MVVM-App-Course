package com.mvvm.gamermvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mvvm.gamermvvmapp.core.Constants.USERS
import com.mvvm.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.mvvm.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.mvvm.gamermvvmapp.domain.repository.AuthRepository
import com.mvvm.gamermvvmapp.domain.repository.UsersRepository
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mvvm.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.mvvm.gamermvvmapp.domain.use_cases.auth.LogOut
import com.mvvm.gamermvvmapp.domain.use_cases.auth.Login
import com.mvvm.gamermvvmapp.domain.use_cases.auth.SignUp
import com.mvvm.gamermvvmapp.domain.use_cases.users.Create
import com.mvvm.gamermvvmapp.domain.use_cases.users.GetUserById
import com.mvvm.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // ESTA ES LA CONECCION ENTRE LA INTERFAZ Y LA CLASE QUE LA IMPLEMENTA
    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    // ESTA ES LA CONECCION ENTRE LA INTERFAZ Y LA CLASE QUE LA IMPLEMENTA
    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        // ac
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logOut = LogOut(repository),
        signUp = SignUp(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository)
    )
}