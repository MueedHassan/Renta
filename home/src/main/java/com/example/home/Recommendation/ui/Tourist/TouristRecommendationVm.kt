package com.example.home.Recommendation.ui.Tourist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.Recommendation.ui.remote.function.PostService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TouristRecommendationVm : ViewModel() {
    var newresponse: List<PostResponse>? = emptyList()
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    fun getrec(cityName:String, cityLocation:String, cityRange:String): List<PostResponse>?
    {  print("hehe getrec")
        viewModelScope.launch((Dispatchers.IO)){
             val service=PostService.create()
            if (cityLocation.isNotEmpty() && cityName.isNotEmpty() && cityRange.isNotEmpty()) {
                try {

                        withContext(Dispatchers.IO){
                            val postResponse = service.createPost(cityName=cityName, postRequest = cityLocation, cityRange=cityRange)
                            withContext(Dispatchers.Main){
                               newresponse=postResponse
                            }
                        }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            else {
                // Handle the case where any of the variables are empty
                // Maybe show an error message or log a warning
            }
        }
        return newresponse
    }

    fun addToFavourites(postResponse: PostResponse) {
            firestore
                .collection("Touristfavourite").document(postResponse.toString())
                .set(postResponse)
                .addOnSuccessListener {
                    // Successfully added to favorites
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }

    }

    fun removeFromFavourites(postResponse: PostResponse) {
        val userId = auth.currentUser?.uid
        userId?.let { uid ->
            firestore.collection("users").document(uid)
                .collection("Touristfavourite").document(postResponse.toString())
                .delete()
                .addOnSuccessListener {
                    // Successfully removed from favorites
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }
        }
    }

}

