package com.example.home.Recommendation.ui.remote.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TenantPostResponse(
    @SerialName("Location")
    val location: String,
    @SerialName("Area")
    val area: String,
    @SerialName("Marla")
    val marla: Int,
    @SerialName("Latitude")
    val latitude: Double,
    @SerialName("Longitude")
    val longitude: Double,
    @SerialName("Bathrooms")
    val bathrooms: Int,
    @SerialName("Bedrooms")
    val bedrooms: Int,
    @SerialName("Rent(K)")
    val rent: Int,
    @SerialName("Crime Rate")
    val crimeRate: String
)