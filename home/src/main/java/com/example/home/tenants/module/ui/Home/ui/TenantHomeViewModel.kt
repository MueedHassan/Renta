package com.example.home.tenants.module.ui.Home.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.tenants.module.ui.Home.domain.Property
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class TenantHomeViewModel:ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties

//    fun getProperties() {
//        viewModelScope.launch {
//            try {
//                val querySnapshot = firestore
//                    .collection("Property Data").get().addOnSuccessListener {documents ->
//                        val propertiesList = mutableListOf<Property>()
//                        for (document in documents) {
//                            println("property $document")
//                            val property = document.toObject(Property::class.java)
//                            println("property ${Property}")
//                            if (property != null) {
//                                propertiesList.add(property)
//                            }
//                        }
//                        _properties.value = propertiesList
//                    }
//            } catch (e: Exception) {
//                // Handle exception
//            }
//        }
//    }
//fun getProperties() {
//    viewModelScope.launch {
//        try {
//            val querySnapshot = firestore.collection("Property Data").get().await()
//            val propertiesList = mutableListOf<Property>()
//            for (document in querySnapshot.documents) {
//                val property = document.toObject(Property::class.java)
//                property?.let {
//                    propertiesList.add(it)
//                }
//            }
//            _properties.value = propertiesList
//        } catch (e: Exception) {
//            // Handle exception
//            e.printStackTrace()
//        }
//    }
//}
fun getProperties() {
    viewModelScope.launch {
        try {
            val querySnapshot = firestore.collection("Property Data").get().await()
            val propertiesList = mutableListOf<Property>()
            for (document in querySnapshot.documents) {
                val property = document.toObject(Property::class.java)
                if (property != null) {
                    propertiesList.add(property)
                } else {
                    Log.e(TAG, "Failed to map document to Property: $document")
                }
            }
            _properties.value = propertiesList
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching properties", e)
        }
    }
}


}