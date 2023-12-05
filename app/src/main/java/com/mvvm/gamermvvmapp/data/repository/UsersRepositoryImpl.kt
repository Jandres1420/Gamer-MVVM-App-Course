package com.mvvm.gamermvvmapp.data.repository

import android.net.Uri
import androidx.compose.runtime.snapshotFlow
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.model.User
import com.mvvm.gamermvvmapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import java.lang.Exception
import javax.inject.Inject
// importante saaber que en la injeccion se dice que userRef esta en la coleccion User
class UsersRepositoryImpl @Inject constructor(
    private val userRef: CollectionReference,
    private val storageUsersRef: StorageReference
): UsersRepository {

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

    override suspend fun update(user: User): Response<Boolean> {
        return try {

            val map: MutableMap<String, Any > = HashMap()

            map["username"] = user.username
            map["image"] = user.image
            // para actualizar en firestore nos metemos a document del usuario por id y el metodo update solo
            // recibe un map con los campos a a ctualizar
            userRef.document(user.id).update(map).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            return  Response.Success(url.toString())
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