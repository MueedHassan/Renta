package com.example.home.tenants.module.ui.Home.domain
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class Property(
    @SerialName("Area")
    val area: String = "",
    @SerialName("Bedroom")
    val bedroom: String = "",
    @SerialName("City")
    val city: String = "",
    @SerialName("Description")
    val description: String = "",
    @SerialName("Location")
    val location: String = "",
    @SerialName("MaxOccupancy")
    val maxOccupancy: String = "",
    @SerialName("PlaceType")
    val placeType: String? = null,
    @SerialName("Price")
    val price: String = "",
    @SerialName("PropertyStatus")
    val propertyStatus: String = "",
    @SerialName("RenterStatus")
    val renterStatus: String = "",
    @SerialName("Title")
    val title: String = "",
    @SerialName("Washroom")
    val washroom: String = "",
    @SerialName("ownerId")
    val ownerId: String = "",
    @SerialName("phoneNumber")
    val phoneNumber: String = "",
    @SerialName("timestamp")
    val timestamp: String = ""
)
