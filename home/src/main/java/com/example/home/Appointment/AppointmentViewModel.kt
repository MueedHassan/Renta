package com.example.home.Appointment

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AppointmentViewModel:ViewModel() {
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    fun formatDate(selectedDateMillis: Long?): String? {
        val selectedDate = Calendar.getInstance().apply {
            timeInMillis = selectedDateMillis ?: System.currentTimeMillis()
        }
        return if (selectedDate.after(Calendar.getInstance())) {
            dateFormatter.format(selectedDate.time)
        } else {
            null
        }
    }
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())

    fun formatTime(hours: Int, minutes: Int): String {
        val time = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
        }
        return timeFormatter.format(time.time)
    }
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun storeAppointment(name: String, date: String, time: String) {
        val appointment = hashMapOf(
            "userId" to auth.currentUser?.uid,
            "name" to name,
            "date" to date,
            "time" to time,
            "approved" to "No"
        )

        firestore.collection("appointments")
            .add(appointment)
            .addOnSuccessListener { documentReference ->
                // Appointment stored successfully
            }
            .addOnFailureListener { e ->
                // Error occurred while storing the appointment
            }
    }


}