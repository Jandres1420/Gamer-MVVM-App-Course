package com.mvvm.gamermvvmapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.lang.reflect.Constructor
import javax.inject.Inject
// y además se pone e injecta aesa instancia desde el contructor sin ser instanciada
class AuthRepositoryImpl @Inject constructor (private val firebaseAuth: FirebaseAuth) : AuthRepository{

    // esto no deberia de estar acá si no en la carpeta DI, DEPENEDNCY INJECTION
    //    val firebaseAuth = FirebaseAuth.getInstance()

    // esto nos devolveria el usuario de sesion si existe
    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {
        return try {
            // ANTES SE USABA EL ONCOMPLELISTENER , PERO ES MEJOR TENER EL AWAIT YA QUE ES ASINCRONO Y ES UNA CORRUTINA
            // YA QUE NO SABEMOS CUANTO TIEMPO SE DEMORE EN RESPONDER EL SERVIDOR
            // IMPORTANTE CADA VEZ QUE SE USEN CORRUTINAS SE USARAEL SUSPEND
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Response.Success(result.user!!)
        }catch(e:Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}