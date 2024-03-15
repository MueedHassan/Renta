package com.example.home.addnewproperty.ui.Components.ui.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

object FirestoreRepository {
    private val db = Firebase.firestore
    private val propertyCollection = db.collection("Property Data")
    private var documentReference: String? = null

    fun getPropertyCollection(): CollectionReference = propertyCollection

    fun getDocumentReference(): String? = documentReference

    fun setDocumentReference(reference: String?) {
        documentReference = reference
    }
}
