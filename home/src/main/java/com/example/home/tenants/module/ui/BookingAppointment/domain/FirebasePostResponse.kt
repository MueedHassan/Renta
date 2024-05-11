package com.example.home.tenants.module.ui.BookingAppointment.domain

import com.example.home.Recommendation.ui.remote.data.PostResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class FirebasePostResponse(
    @SerialName("postResponse")
    val postResponse: PostResponse = PostResponse(),
    @SerialName("Start Date")
    val startDate: String = "",
    @SerialName("End Date")
    val endDate: String = "",
    val adults: Int = 0,
    val children: Int = 0,
    @SerialName("userId")
    val userId: String = ""
)

