package com.example.home.Recommendation.ui.ui
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.home.Recommendation.ui.Tourist.getPostResponse
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BookingViewModel : ViewModel() {
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
    fun bookHotel(adults: Int, children: Int, startdate:String,enddate:String,context: Context) {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val postResponse: PostResponse?= getPostResponse(context=context, key ="selected_post_response")
        val database = FirebaseFirestore.getInstance()
        val bookingsRef = database.collection("bookings")
        val bookingData = mapOf(
            "userId" to userId,
            "postResponse" to postResponse,
            "adults" to adults,
            "children" to children,
            "Start Date" to startdate,
            "End Date" to enddate
        )
         bookingsRef.add(bookingData)
    }
}
