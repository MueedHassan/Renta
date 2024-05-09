package com.example.home.Recommendation.ui.Tenant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.home.Recommendation.ui.remote.data.TenantPostResponse
import com.example.home.Recommendation.ui.remote.function.PostService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TenantRecommendationVm: ViewModel() {
    var newresponse: List<TenantPostResponse>? = emptyList()
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    fun getrec(loc1:String,
               loc2:String,
               loc3:String,
               range:String): List<TenantPostResponse>?
    {  print("hehe getrec")
        viewModelScope.launch((Dispatchers.IO)){
            val service= PostService.create()
            if (loc1.isNotEmpty() && loc2.isNotEmpty() && loc3.isNotEmpty() && loc3.isNotEmpty() && range.isNotEmpty()) {
                try {

                    withContext(Dispatchers.IO){
                        val postResponse = service.createTenantPost(loc1=loc1, loc2= loc2, loc3 = loc3,range=range)
                        withContext(Dispatchers.Main){
                            newresponse=postResponse
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                // Handle the case where any of the variables are empty
                // Maybe show an error message or log a warning
            }
        }
        return newresponse
    }

    fun addToFavourites(postResponse: TenantPostResponse) {
        val userId = auth.currentUser?.uid
        userId?.let { uid ->
            firestore.collection("users").document(uid)
                .collection("Tenantfavourite").document(postResponse.toString())
                .set(postResponse)
                .addOnSuccessListener {
                    // Successfully added to favorites
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }
        }
    }

    fun removeFromFavourites(postResponse: TenantPostResponse) {
        val userId = auth.currentUser?.uid
        userId?.let { uid ->
            firestore.collection("users").document(uid)
                .collection("Tenantfavourite").document(postResponse.toString())
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
