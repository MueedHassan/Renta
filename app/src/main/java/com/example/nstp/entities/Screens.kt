package com.example.nstp.entities

sealed class Screen(
    val route:String
){
    data object SignIn:Screen("SignIn")
    data object MainHome:Screen("HomeMain")
}
