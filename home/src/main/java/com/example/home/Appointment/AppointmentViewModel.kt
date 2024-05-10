package com.example.home.Appointment

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.home.Recommendation.ui.Tourist.getPostResponse
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.Recommendation.ui.remote.data.TenantPostResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AppointmentViewModel:ViewModel() {
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
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
    fun formatTime(hours: Int, minutes: Int): String {
        val time = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hours)
            set(Calendar.MINUTE, minutes)
        }
        return timeFormatter.format(time.time)
    }

    fun storeAppointment(name: String, date: String, time: String,context: Context) {
        val postResponse: TenantPostResponse?= getPostResponse(context=context, key ="selected_Tenant_post_response")
        val appointment = hashMapOf(
            "userId" to auth.currentUser?.uid,
            "name" to name,
            "date" to date,
            "time" to time,
            "approved" to "No",
            " TenantPostResponse" to postResponse
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

   private fun getPostResponse(context: Context, key: String): TenantPostResponse? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(key, null)
        val gson = Gson()
        return gson.fromJson(json, TenantPostResponse::class.java)
    }
}