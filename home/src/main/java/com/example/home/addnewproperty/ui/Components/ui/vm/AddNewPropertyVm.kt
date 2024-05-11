package com.example.home.addnewproperty.ui.Components.ui.vm


import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.home.addnewproperty.ui.Components.ui.repo.AddNewPropertyScreen1
import com.example.home.addnewproperty.ui.Components.ui.repo.FirebaseImageRepository
import com.example.home.addnewproperty.ui.Components.ui.repo.GetRecentID
import com.example.home.addnewproperty.ui.Components.ui.repo.UpdateDoc
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

public class AddNewPropertyViewModel(
    private val addNewPropertyScreen1: AddNewPropertyScreen1,
    private val getRecentID: GetRecentID,
    private val UpdateDoc:UpdateDoc,
    private val firebaseImageRepository: FirebaseImageRepository

) : ViewModel() {
    var propertyId: String? = null
        private set

    suspend fun addProperty(data: HashMap<String, Any?>): String? {
        propertyId = addNewPropertyScreen1.addData(data)
        println("add $propertyId")
        return propertyId
    }
     fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }


    suspend fun getRecentId(): String? {
        return getRecentID.getMostRecentDocumentId()
    }

    suspend fun UpdateDoc(updatedData: HashMap<String, Any?>, id: String?) {
        UpdateDoc.updateDataForDocumentId(id, updatedData)
    }

    suspend fun uploadImageAndSaveUrl(uri: Uri): String? {
        return withContext(Dispatchers.IO) {
            firebaseImageRepository.uploadImageToStorage(uri)
        }
    }

    suspend fun uploadImagesAndSaveUrls(uris: List<Uri>): List<String?> {
        return withContext(Dispatchers.IO) {
            firebaseImageRepository.uploadImagesAndSaveUrls(uris)
        }

    }
}


//
//class AddNewPropertyVm : ViewModel(){
//    private val propertyCollection = FirestoreRepository.getPropertyCollection()
//    private val documentReference = FirestoreRepository.getDocumentReference()
//    val db = Firebase.firestore.collection("Property Data")
//    var propertyData = mutableStateOf<Property?>(null)
//    var documentId by
//        mutableStateOf<String?>(null)
//        private set
//    fun AddDataScreen1(data: HashMap<String, String?>) {
//        db.add(data)
//            .addOnSuccessListener {
//                documentReference ->
//                // Save the document reference
//                FirestoreRepository.setDocumentReference(documentReference.id)
//                saveDocumentId(documentReference.id)
//                println("idking ${documentReference.id}")
//                println("idking $documentId")
//            }.addOnFailureListener { e ->
//                // Handle failure
//            }
//        return
//    }
//    fun saveDocumentId(id: String) {
//        viewModelScope.launch {
//            documentId = id
//            println("idking $documentId")
//        }
//    }
//    fun updateDataForDocumentId(updatedData: HashMap<String, Any?>) {
//        val id = documentReference
//        println("idking $documentId")
//        println("idking $id")
//
//            if (id != null) {
//                println("idin $id")
//                db.document(id)
//                    .update(updatedData)
//                    .addOnSuccessListener{
//                    }
//                    .addOnFailureListener { e ->
//
//                    }
//            }
//
//
//
//
//    }
//
//}