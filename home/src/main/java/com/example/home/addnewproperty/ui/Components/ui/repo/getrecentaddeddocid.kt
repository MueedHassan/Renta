package com.example.home.addnewproperty.ui.Components.ui.repo

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await

interface GetRecentID {
    suspend fun getMostRecentDocumentId(): String?
}

class GetRecentIDImpl : GetRecentID {
    private val db: CollectionReference = FirebaseFirestore.getInstance().collection("Property Data")

    override suspend fun getMostRecentDocumentId(): String? {
        val query = db
            .orderBy("timestamp", Query.Direction.DESCENDING)
            .limit(1)
        return try {
            val querySnapshot = query.get().await()
            if (!querySnapshot.isEmpty) {
                querySnapshot.documents[0].id
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
