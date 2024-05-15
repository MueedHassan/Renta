package com.example.home.landlord.domain
import com.example.home.tenants.module.ui.Home.domain.Property
import kotlinx.serialization.Contextual
data class BookingProperty(
    val enddate: String = "",
    val startdate: String = "",
    val adults: Int = 0,
    val children: Int = 0,
    val ownerId: String = "",
    val userId: String = "",
    val approved: String = "",
    val title: String = "",
    val image: String = "",
)