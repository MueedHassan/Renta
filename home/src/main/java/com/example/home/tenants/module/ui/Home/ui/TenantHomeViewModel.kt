package com.example.home.tenants.module.ui.Home.ui

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.tenants.module.ui.Home.domain.Property
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TenantHomeViewModel:ViewModel() {
    private val firestore = FirebaseFirestore.getInstance()
    private val _properties = MutableStateFlow<List<Property>>(emptyList())
    val properties: StateFlow<List<Property>> = _properties
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

    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
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
        val postResponse: Property?= getPostResponse(context=context, key ="property")
        val appointment = hashMapOf(
            "userId" to auth.currentUser?.uid,
            "ownerId" to (postResponse?.ownerId),
            "name" to name,
            "date" to date,
            "time" to time,
            "approved" to "No",
            "title" to (postResponse?.Title.toString()),
            "image" to if(postResponse?.List_Of_Images_Uris!!.isNotEmpty())
                    {
                        postResponse.List_Of_Images_Uris[0]
                    }
                    else{
                        "https://firebasestorage.googleapis.com/v0/b/my-fyp-renta.appspot.com/o/images%2F1715471092756.jpg?alt=media&token=8869a564-0cc6-48f0-8ca1-3c01b95a7e67"
                    }

        )
        firestore.collection("searchedappointments")
            .add(appointment)
            .addOnSuccessListener { documentReference ->
            }
            .addOnFailureListener { e ->
            }
    }
    fun bookHotel(adults: Int, children: Int, startdate:String,enddate:String,context: Context) {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val postResponse: Property?= getPostResponse(
            context = context,
            key = "property"
        )
        val database = FirebaseFirestore.getInstance()
        val bookingsRef = database.collection("searchedbookings")
        val bookingData = mapOf(
            "userId" to userId,
            "adults" to adults,
            "children" to children,
            "startdate" to startdate,
            "enddate" to enddate,
            "ownerId" to (postResponse?.ownerId),
            "approved" to "No",
            "title" to (postResponse?.Title.toString()),
            "image" to
                    if(postResponse?.List_Of_Images_Uris!![0].isNotEmpty())
                    {
                        postResponse.List_Of_Images_Uris[0]
                    }
                    else{
                        "https://firebasestorage.googleapis.com/v0/b/my-fyp-renta.appspot.com/o/images%2F1715471092756.jpg?alt=media&token=8869a564-0cc6-48f0-8ca1-3c01b95a7e67"
                    }
        )
        bookingsRef.add(bookingData)
    }
    private fun getPostResponse(context: Context, key: String): Property? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(key, null)
        val gson = Gson()
        return gson.fromJson(json, Property::class.java)
    }
//

}