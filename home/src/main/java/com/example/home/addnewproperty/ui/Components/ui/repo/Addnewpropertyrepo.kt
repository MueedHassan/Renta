package com.example.home.addnewproperty.ui.Components.ui.repo
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

interface AddNewPropertyScreen1 {
    suspend fun addData(data: HashMap<String, Any?>): String?
}
class AddNewPropertyScreen1Impl : AddNewPropertyScreen1 {
    private val db: CollectionReference = FirebaseFirestore.getInstance().collection("Property Data")

    override suspend fun addData(data: HashMap<String, Any?>): String? {
        val documentRef = db.add(data).await()
        return documentRef.id
    }
}
