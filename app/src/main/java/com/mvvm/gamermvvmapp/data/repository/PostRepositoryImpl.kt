package com.mvvm.gamermvvmapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import com.mvvm.gamermvvmapp.core.Constants.POSTS
import com.mvvm.gamermvvmapp.domain.model.Post
import com.mvvm.gamermvvmapp.domain.model.Response
import com.mvvm.gamermvvmapp.domain.repository.PostRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named

class PostRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference,
): PostRepository{
    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow{
        val snapshotListener = postRef.addSnapshotListener{ snapshot, error ->
            val postResponse = if (snapshot != null) {
                val posts = snapshot.toObjects(Post::class.java)
                Response.Success(posts)
            }else{
                Response.Failure(error)
            }
            trySend(postResponse)
        }
        awaitClose{
            snapshotListener.remove()
        }
    }

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try{
            //IMAGE
            val fromFile = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            //DATA
            post.image = url.toString()
            postRef.add(post).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Failure(e)
        }
    }
}