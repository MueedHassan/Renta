package com.example.home.Recommendation.ui.remote.data

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val name:String,
    val city:String,
    val range:String
)
