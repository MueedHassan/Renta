package com.example.home.Recommendation.ui.remote.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TenantPostResponse(
    @SerialName("Location")
    val location: String="",
    @SerialName("Area")
    val area: String="",
    @SerialName("Marla")
    val marla: Int=0,
    @SerialName("Latitude")
    val latitude: Double=0.0,
    @SerialName("Longitude")
    val longitude: Double=0.0,
    @SerialName("Bathrooms")
    val bathrooms: Int=0,
    @SerialName("Bedrooms")
    val bedrooms: Int=0,
    @SerialName("Rent(K)")
    val rent: Int=0,
    @SerialName("Crime Rate")
    val crimeRate: String=""
)