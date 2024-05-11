package com.example.home.tenants.module.ui.BookingAppointment.domain

import com.example.home.Recommendation.ui.remote.data.TenantPostResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseTenantPostResponse(
    @SerialName("TenantPostResponse")
    val TenantPostResponse:TenantPostResponse=TenantPostResponse(),
    val approved:String="",
    val date:String="",
    val name:String="",
    val time:String="",
    val userId:String="",
)
