package com.example.home.landlord.ui

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applovin.exoplayer2.b.e
import com.example.home.tenants.module.ui.Home.domain.Property
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ListingViewModel:ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties
    init {
        getProperties()
    }
    fun getProperties() {
        viewModelScope.launch {
            try {
                val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
                currentUserId?.let { userId ->
                    val querySnapshot = firestore.collection("Property Data")
                        .whereEqualTo("ownerId", userId)
                        .get()
                        .await()
                    val propertiesList = mutableListOf<Property>()
                    for (document in querySnapshot.documents) {
                        val property = document.toObject(Property::class.java)
                        property?.let {
                            propertiesList.add(property)
                        } ?: Log.e(TAG, "Failed to map document to Property: $document")
                    }
                    _properties.value = propertiesList
                } ?: Log.e(TAG, "Current user ID is null")
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching properties", e)
            }
        }
    }

}