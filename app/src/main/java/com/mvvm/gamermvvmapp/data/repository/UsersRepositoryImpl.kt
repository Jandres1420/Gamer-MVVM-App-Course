package com.mvvm.gamermvvmapp.data.repository

import androidx.compose.runtime.snapshotFlow
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val userRef: CollectionReference): UsersRepository {

    // la manera sin injeccion de dependencias seria
//    val firebase = Firebase.firestore
//    val usersRef = firebase.collection("Users")

    override suspend fun create(user: User): Response<Boolean> {
        return try {
            // crear un documento y poner un id Y ADEMÁS TODA LA INFORMACIÓN QUE TRAIGA EL USUARIO, y la contraseña no la vamos a guardar de ninguna manera
            // por seguridad
            user.password = ""
            userRef.document(user.id).set(user).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }

    }

    // al usar flow, podemos usar el metodo trySend() este metodo emitira la información cuando sea requerida
    override fun getUserById(id: String): Flow<User> = callbackFlow{
        // sera el escuchador de la inforamción que envie firebase, por lo que esta información cambia en tiempo real
        val snapshotListener = userRef.document(id).addSnapshotListener{
            // snapshot = informacion  , e = error
                snapshot, e ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }

        awaitClose{
            // para que no todos los objetos se queden escuchando siempre
            snapshotListener.remove()
        }
    }

}