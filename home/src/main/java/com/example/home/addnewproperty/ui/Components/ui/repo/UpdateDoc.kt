package com.example.home.addnewproperty.ui.Components.ui.repo

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

interface UpdateDoc {
    suspend fun updateDataForDocumentId(documentId: String?, updatedData: HashMap<String, Any?>)
}
class UpdateDocImpl :UpdateDoc {
    private val db: CollectionReference = FirebaseFirestore.getInstance().collection("Property Data")
    override suspend fun updateDataForDocumentId(documentId: String?, updatedData: HashMap<String, Any?>) {
        try {
            if (documentId != null) {
                db.document(documentId)
                    .update(updatedData)
                    .await()
            }
        }
        catch
            (e: Exception)
        {}
    }
}
