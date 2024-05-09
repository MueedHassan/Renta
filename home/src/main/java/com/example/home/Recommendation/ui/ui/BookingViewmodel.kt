package com.example.home.Recommendation.ui.ui
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.home.Recommendation.ui.Tourist.getPostResponse
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class BookingViewModel : ViewModel() {
    fun bookHotel(adults: Int, children: Int, startdate:String,enddate:String,context: Context) {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val userId = currentUser?.uid
        val postResponse: PostResponse?= getPostResponse(context=context, key ="selected_post_response")
        // Add booking data to Firebase Realtime Database
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
