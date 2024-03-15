package com.example.home.addnewproperty.ui.Components.ui.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.addnewproperty.ui.Components.ui.entities.Property
import com.example.home.addnewproperty.ui.Components.ui.repo.FirestoreRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch


class AddNewPropertyVm : ViewModel(){
    private val propertyCollection = FirestoreRepository.getPropertyCollection()
    private val documentReference = FirestoreRepository.getDocumentReference()
    val db = Firebase.firestore.collection("Property Data")
    var propertyData = mutableStateOf<Property?>(null)
    var documentId by
        mutableStateOf<String?>(null)
        private set
    fun AddDataScreen1(data: HashMap<String, String?>) {
        db.add(data)
            .addOnSuccessListener {
                documentReference ->
                // Save the document reference
                FirestoreRepository.setDocumentReference(documentReference.id)
                saveDocumentId(documentReference.id)
                println("idking ${documentReference.id}")
                println("idking $documentId")
            }.addOnFailureListener { e ->
                // Handle failure
            }
        return
    }
    fun saveDocumentId(id: String) {
        viewModelScope.launch {
            documentId = id
            println("idking $documentId")
        }
    }
    fun updateDataForDocumentId(updatedData: HashMap<String, Any?>) {
        val id = documentReference
        println("idking $documentId")
        println("idking $id")

            if (id != null) {
                println("idin $id")
                db.document(id)
                    .update(updatedData)
                    .addOnSuccessListener{
                    }
                    .addOnFailureListener { e ->

                    }
            }




    }

}