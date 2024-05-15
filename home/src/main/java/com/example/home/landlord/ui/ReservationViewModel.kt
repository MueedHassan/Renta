package com.example.home.landlord.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.landlord.domain.Appointment
import com.example.home.landlord.domain.BookingProperty
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ReservationViewModel:ViewModel() {

    private val _searchedAppointments = MutableStateFlow<List<Appointment>>(emptyList())
    val searchedAppointments: StateFlow<List<Appointment>> get() = _searchedAppointments

    private val _searchedBookings= MutableStateFlow<List<BookingProperty>>(emptyList())
    val searchedBookings: StateFlow<List<BookingProperty>> get() = _searchedBookings
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    init {
        getSearchedAppointmentsByOwner()
        getSearchedBookingsByOwner()
    }
    fun getSearchedAppointmentsByOwner() {
        val currentUser = auth.currentUser
        val ownerId = currentUser?.uid ?: return  // Return if current user is null
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val querySnapshot = firestore.collection("searchedappointments")
                    .whereEqualTo("ownerId", ownerId)
                    .get()
                    .await()

                val appointmentsList = mutableListOf<Appointment>()
                for (document in querySnapshot.documents) {
                    val appointment = document.toObject(Appointment::class.java)
                    appointment?.let {
                        appointmentsList.add(it)
                    }
                }
                _searchedAppointments.value = appointmentsList
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
    fun getSearchedBookingsByOwner() {
        val currentUser = auth.currentUser
        val ownerId = currentUser?.uid ?: return  // Return if current user is null
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val querySnapshot = firestore.collection("searchedbookings")
                    .whereEqualTo("ownerId", ownerId)
                    .get()
                    .await()

                val appointmentsList = mutableListOf<BookingProperty>()
                for (document in querySnapshot.documents) {
                    val appointment = document.toObject(BookingProperty::class.java)
                    appointment?.let {
                        appointmentsList.add(it)
                    }
                }
                _searchedBookings.value = appointmentsList
            } catch (e: Exception) {
                // Handle errors
            }
        }
    }
}