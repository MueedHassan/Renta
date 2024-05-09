package com.example.home.tenants.module.ui.Favourite

import androidx.lifecycle.ViewModel
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavouritesViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val _favourites = MutableStateFlow<List<PostResponse>>(emptyList())
    val favourites: StateFlow<List<PostResponse>> = _favourites

    fun fetchFavouritesHotel() {
        val userId = auth.currentUser?.uid
        userId?.let { uid ->
            firestore.collection("users").document(uid)
                .collection("Touristfavourite")
                .get()
                .addOnSuccessListener { documents ->
                    val favouritesList = mutableListOf<PostResponse>()
                    for (document in documents) {
                        val postResponse = document.toObject(PostResponse::class.java)
                        favouritesList.add(postResponse)
                    }
                    _favourites.value = favouritesList
                }
                .addOnFailureListener { exception ->
                    // Handle failure
                }
        }
    }
    fun fetchFavouritesHome() {
        val userId = auth.currentUser?.uid
        userId?.let { uid ->
            firestore.collection("users").document(uid)
                .collection("Tenantfavourite")
                .get()
                .addOnSuccessListener { documents ->
                    val favouritesList = mutableListOf<PostResponse>()
                    for (document in documents) {
                        val postResponse = document.toObject(PostResponse::class.java)
                        favouritesList.add(postResponse)
                    }
                    _favourites.value = favouritesList
                }
                .addOnFailureListener { exception ->
                    // Handle failure
                }
        }
    }
}
