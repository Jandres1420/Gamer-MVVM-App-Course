package com.mvvm.gamermvvmapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mvvm.gamermvvmapp.core.Constants.POSTS
import com.mvvm.gamermvvmapp.core.Constants.USERS
import com.mvvm.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.mvvm.gamermvvmapp.data.repository.PostRepositoryImpl
import com.mvvm.gamermvvmapp.data.repository.UsersRepositoryImpl
import com.mvvm.gamermvvmapp.domain.repository.AuthRepository
import com.mvvm.gamermvvmapp.domain.repository.PostRepository
import com.mvvm.gamermvvmapp.domain.repository.UsersRepository
import com.mvvm.gamermvvmapp.domain.use_cases.auth.AuthUseCases
import com.mvvm.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.mvvm.gamermvvmapp.domain.use_cases.auth.LogOut
import com.mvvm.gamermvvmapp.domain.use_cases.auth.Login
import com.mvvm.gamermvvmapp.domain.use_cases.auth.SignUp
import com.mvvm.gamermvvmapp.domain.use_cases.posts.CreatePost
import com.mvvm.gamermvvmapp.domain.use_cases.posts.GetPosts
import com.mvvm.gamermvvmapp.domain.use_cases.posts.PostsUseCases
import com.mvvm.gamermvvmapp.domain.use_cases.users.Create
import com.mvvm.gamermvvmapp.domain.use_cases.users.GetUserById
import com.mvvm.gamermvvmapp.domain.use_cases.users.SaveImage
import com.mvvm.gamermvvmapp.domain.use_cases.users.Update
import com.mvvm.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

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
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    // Esta es la carpeta que se crea en storage con el nombre "Users
    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference =
        storage.reference.child(USERS)

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    // Esta es la carpeta que se crea en storage con el nombre "post
    @Provides
    @Named(POSTS)
    fun provideStoragePostsRef(storage: FirebaseStorage): StorageReference  = storage.reference.child(POSTS)

    // firestore collection post
    // IMPORTANTE CUANDO SON DEL MISMO TIPO como lo son los metodos providePostsRef provideUsersRef que son de tipo  CollectionReference
    // saldra duplicate binding, porque no sabra cual diferenciar al momento de la injeccion, cual de los dos metodos, se resuelve con la anotación NAMEd
    @Provides
    @Named(POSTS)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    // ESTA ES LA CONECCION ENTRE LA INTERFAZ POSTREPOSITORY Y LA CLASE QUE LA IMPLEMENTA POSTREPOSITORYIMPL
    @Provides
    fun providePostsRepository(impl: PostRepositoryImpl): PostRepository = impl

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
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun providePostsUseCases(repository: PostRepository) = PostsUseCases(
        create = CreatePost(repository),
        getPosts = GetPosts(repository)
    )
}