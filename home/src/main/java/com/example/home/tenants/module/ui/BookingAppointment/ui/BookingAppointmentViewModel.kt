package com.example.home.tenants.module.ui.BookingAppointment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.tenants.module.ui.BookingAppointment.domain.FirebasePostResponse
import com.example.home.tenants.module.ui.BookingAppointment.domain.FirebaseTenantPostResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class BookingAppointmentViewModel: ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _bookings = MutableStateFlow<List<FirebasePostResponse>>(emptyList())
    val bookings: MutableStateFlow<List<FirebasePostResponse>> = _bookings
    private val _appointment = MutableStateFlow<List<FirebaseTenantPostResponse>>(emptyList())
    val appointments: MutableStateFlow<List<FirebaseTenantPostResponse>> = _appointment
    private fun getCurrentUserId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid
    }

    fun getBookings() {
        val userId=getCurrentUserId()
        userId?.let { uid ->
            firestore.collection("bookings").whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    val resultList = mutableListOf<FirebasePostResponse>()
                    for (document in documents) {
                        val data = document.data
                        println("Firebase ${document.data}")
                        val postResponse = document.toObject(FirebasePostResponse::class.java)
                        println("Firebase ${postResponse}")
                        resultList.add(postResponse)
                    }
                    _bookings.value = resultList
                }
                .addOnFailureListener { exception ->
                }
        }

    }
    fun getAppointment(){
        val userId=getCurrentUserId()
        userId?.let { uid ->
            firestore.collection("appointments").whereEqualTo("userId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    val resultList = mutableListOf<FirebaseTenantPostResponse>()
                    for (document in documents) {
                        val data = document.data
                        println("Firebase ${document.data}")
                        val postResponse = document.toObject(FirebaseTenantPostResponse::class.java)
                        println("Firebase ${postResponse}")
                        resultList.add(postResponse)
                    }
                    _appointment.value = resultList
                }
                .addOnFailureListener { exception ->
                }
        }

    }
}
