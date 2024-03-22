package com.example.home.addnewproperty.ui.Components.ui.repo

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

// Define an interface for Firebase operations related to images
interface FirebaseImageRepository {
    suspend fun uploadImageToStorage(uri: Uri): String?
    suspend fun uploadImagesAndSaveUrls(uris: List<Uri>): List<String?>
}

// Implement the interface
class FirebaseImageRepositoryImpl : FirebaseImageRepository {
    private val db = FirebaseFirestore.getInstance().collection("Property Data")
    private val storageRef = FirebaseStorage.getInstance().reference
    override suspend fun uploadImageToStorage(uri: Uri): String? {
        val imageName = "${System.currentTimeMillis()}.jpg"
        val imageRef = storageRef.child("images/$imageName")
        return try {
            imageRef.putFile(uri).await()
            imageRef.downloadUrl.await().toString()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    override suspend fun uploadImagesAndSaveUrls(uris: List<Uri>): List<String?> {
        val imageUrls = mutableListOf<String?>()
        for (uri in uris) {
            val imageUrl = uploadImageToStorage(uri)
            if (imageUrl != null) {
                val imageDocument = hashMapOf(
                    "imageUrl[$uri]" to imageUrl
                )
                try {
                    imageUrls.add(imageUrl)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return imageUrls
    }
}
