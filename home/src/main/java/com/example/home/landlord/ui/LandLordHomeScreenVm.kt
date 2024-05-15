package com.example.home.landlord.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class LandLordHomeScreenVm: ViewModel() {
    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName
    private val _ownerIdCount = mutableStateOf(0)
    val ownerIdCount: State<Int> = _ownerIdCount
    private val _searchedAppointmentsCount = mutableStateOf(0)
    val searchedAppointmentsCount: State<Int> = _searchedAppointmentsCount
    private val _searchedBookingsCount = mutableStateOf(0)
    val searchedBookingsCount: State<Int> = _searchedBookingsCount
    init {
        fetchUserName()
        fetchOwnerIdCount()
        fetchSearchedAppointmentsCount()
        fetchSearchedBookingsCount()
    }
    private fun fetchUserName() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid // Get the current user ID
        val db = FirebaseFirestore.getInstance()
        val usersCollection = db.collection("users")

        usersCollection
            .whereEqualTo("userid", userId) // Filter documents where 'userid' field matches current user ID
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val userName = document.getString("name")
                    _userName.value = userName ?: ""
                    return@addOnSuccessListener
                }
            }
            .addOnFailureListener { exception ->
                println("Error fetching user name: $exception")
                _userName.value = ""
            }
    }
    private fun fetchOwnerIdCount() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid
        val db = FirebaseFirestore.getInstance()
        val propertyCollection = db.collection("Property Data")
        propertyCollection
            .whereEqualTo("ownerId", currentUserId)
            .get()
            .addOnSuccessListener { documents ->
                _ownerIdCount.value = documents.size()
            }
            .addOnFailureListener { exception ->
                println("Error fetching ownerId count: $exception")
            }
    }
    private fun fetchSearchedAppointmentsCount() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid
        val db = FirebaseFirestore.getInstance()
        val searchedAppointmentsCollection = db.collection("searchedappointments")
        searchedAppointmentsCollection
            .whereEqualTo("ownerId", currentUserId)
            .get()
            .addOnSuccessListener { documents ->
                _searchedAppointmentsCount.value = documents.size()
            }
            .addOnFailureListener { exception ->
                println("Error fetching searchedAppointments count: $exception")
            }
    }
    private fun fetchSearchedBookingsCount() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val currentUserId = currentUser?.uid
        val db = FirebaseFirestore.getInstance()
        val searchedAppointmentsCollection = db.collection("searchedbookings")
        searchedAppointmentsCollection
            .whereEqualTo("ownerId", currentUserId)
            .get()
            .addOnSuccessListener { documents ->
                _searchedBookingsCount.value = documents.size()
            }
            .addOnFailureListener { exception ->
                println("Error fetching searchedAppointments count: $exception")
            }
    }
}