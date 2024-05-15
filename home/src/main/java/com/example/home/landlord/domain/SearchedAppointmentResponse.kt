package com.example.home.landlord.domain

import com.example.home.tenants.module.ui.Home.domain.Property
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class Appointment(
    val approved: String = "",
    val date: String = "",
    val name: String = "",
    val ownerId: String = "",
    val time: String = "",
    val userId: String = "",
    val title : String = "",
    val image:String=""
)







