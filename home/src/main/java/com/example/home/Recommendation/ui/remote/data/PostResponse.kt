package com.example.home.Recommendation.ui.remote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
     @SerialName("Hotel")
     val hotel: String="",
     @SerialName("Expected User Happiness")
     val expectedUserHappiness: Float=0f,
     @SerialName("Location")
     val location: String="",
     @SerialName("Crime Rate")
     val crimeRate: String="",
     @SerialName("Host")
     val host: String="",
     @SerialName("Image_link")
     val imageLink: String="",
     @SerialName("Guests")
     val guests: String="",
     @SerialName("Bedrooms")
     val bedrooms: String="",
     @SerialName("Bathrooms")
     val bathrooms: String="",
     @SerialName("Price($)")
     val price: Float=0f,
     @SerialName("Review")
     val review: String="",
)
