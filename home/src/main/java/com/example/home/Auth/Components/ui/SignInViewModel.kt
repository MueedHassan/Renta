package com.example.home.Auth.Components.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.home.Auth.Components.repo.SignInResult
import com.example.home.Auth.Components.repo.SignInState
import com.example.home.destinations.MainhomeDestination
import com.example.home.destinations.SignInDestination
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignInViewModel: ViewModel() {
    private  var auth: FirebaseAuth= Firebase.auth
    val firestore= FirebaseFirestore.getInstance()
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()
    var user:FirebaseUser?=null
    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }



//    fun SignInExistingUser(navigator: DestinationsNavigator,email:String,password:String){
//        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this)
//        { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                   navigator.navigate(MainhomeDestination)
//                    val user = auth.currentUser
//                } else {
//                    // If sign in fails, display a message to the user.
//                   println("yoo fail")
//                }
//            }
//
//    }
    fun SignInNewUser(
    navigator: DestinationsNavigator,
    email: String,
    password: String,
    income: String,
    name: String
){
    GlobalScope.launch(Dispatchers.Main) {
        try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            // Sign in success, update UI with the signed-in user's information
            navigator.navigate(SignInDestination)
             user = authResult.user
            if (user != null) {
                println("yo sign ${user!!.uid}")
            }
        } catch (e: Exception) {
            // If sign in fails, display a message to the user.
            println("Sign in failed: ${e.message}")
        }
        if (email.isNotBlank() && password.isNotBlank() && name.isNotBlank() && income.isNotBlank()) {
            val user = hashMapOf(
                "email" to email,
                "name" to name,
                "income" to income,
                "userid" to (user?.uid)
            )

            // Add user data to Firestore

            firestore.collection("users").add(user)
                .addOnSuccessListener {
                    // Handle success
                }
                .addOnFailureListener { e ->
                    // Handle failure
                }
        } else {
            // Handle validation error
        }
    }
    }

    fun signInExistingUser(navigator: DestinationsNavigator, email: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                val authResult = auth.signInWithEmailAndPassword(email, password).await()
                // Sign in success, update UI with the signed-in user's information
                navigator.navigate(MainhomeDestination)
                val user = authResult.user
                if (user != null) {
                    println("yo sign ${user.uid}")
                }
            } catch (e: Exception) {
                // If sign in fails, display a message to the user.
                println("Sign in failed: ${e.message}")
            }
        }
    }

}
