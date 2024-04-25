package com.example.home.SplashScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashScrrenViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences =
        application.getSharedPreferences("MyPrefs", Application.MODE_PRIVATE)

    val currentUser = FirebaseAuth.getInstance().currentUser

    fun isFirstTimeLogin(): Boolean {
        return sharedPreferences.getBoolean(FIRST_TIME_LOGIN_KEY, true)
    }

    fun setFirstTimeLogin(isFirstTime: Boolean) {
        sharedPreferences.edit().putBoolean(FIRST_TIME_LOGIN_KEY, isFirstTime).apply()
    }

    fun currentUser(): FirebaseUser? {
        return currentUser
    }

    companion object {
        private const val FIRST_TIME_LOGIN_KEY = "first_time_login"
    }
}