package com.example.home.Auth.Components.ui

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import com.example.home.Auth.Components.component.FilledButtonExample
import com.example.home.Auth.Components.component.FirstHeading
import com.example.home.Auth.Components.component.SecondHeading
import com.example.home.Auth.Components.component.TextBody
import com.example.home.Auth.Components.component.TextField
import com.example.home.Auth.Components.component.getheight
import com.example.home.Auth.Components.component.getwidth
import com.example.home.Auth.Components.repo.GoogleAuthUiClient
import com.example.home.R
import com.example.home.destinations.MainhomeDestination
import com.example.home.destinations.SignUpPageDestination
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(start=true)
@Composable
fun SignIn(
    navigator:DestinationsNavigator
)
{
// val applicationContext= LocalContext.current
//    val lifecycleScope = rememberCoroutineScope()
      val viewModel = viewModel<SignInViewModel>()
//    val state by viewModel.state.collectAsState()
//
//    val googleAuthUiClient by lazy {
//        GoogleAuthUiClient(
//            context = applicationContext,
//            oneTapClient = Identity.getSignInClient(applicationContext)
//        )
//    }
//
//    LaunchedEffect(key1 = Unit) {
//        if(googleAuthUiClient.getSignedInUser() != null) {
//            navigator.navigate(MainhomeDestination)
//        }
//    }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartIntentSenderForResult(),
//        onResult = { result ->
//            if(result.resultCode == RESULT_OK) {
//                lifecycleScope.launch {
//                    val signInResult = googleAuthUiClient.signInWithIntent(
//                        intent = result.data ?: return@launch
//                    )
//                    viewModel.onSignInResult(signInResult)
//                }
//            }
//        }
//    )

//    LaunchedEffect(key1 = state.isSignInSuccessful) {
//        if(state.isSignInSuccessful) {
//            Toast.makeText(
//                applicationContext,
//                "Sign in successful",
//                Toast.LENGTH_LONG
//            ).show()
//
//            navigator.navigate(MainhomeDestination)
//            viewModel.resetState()
//        }
//    }

//    SignInScreen(
//        state = state,
//        onSignInClick = {
//            lifecycleScope.launch {
//                val signInIntentSender = googleAuthUiClient.signIn()
//                launcher.launch(
//                    IntentSenderRequest.Builder(
//                        signInIntentSender ?: return@launch
//                    ).build()
//                )
//            }
//        }
//    )

    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()
     val REQ_ONE_TAP = 2  // Can be any integer unique to the Activity
     var showOneTapUI = true
    val googleSignInClient = GoogleSignIn.getClient(
        context,
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(R.string.web_client_id.toString()) // Replace with your web client ID
            .requestEmail()
            .build()
    )
    var signInRequest = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                // Your server's client ID, not your Android client ID.
                .setServerClientId((R.string.web_client_id).toString())
                // Only show accounts previously used to sign in.
                .setFilterByAuthorizedAccounts(false)
                .build()
        )
        .build()

    val signInLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.getResult(ApiException::class.java)
            // Use account.idToken to authenticate with Firebase
            println("hehe ${account.email}")
        } else {
            // Handle sign-in failure
            println("fail")
        }
    }


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()

    ){
        FirstHeading(
            modifier= Modifier
                .offset(y = (getheight() * 0.01).dp)
                .align(Alignment.Start),
            symbol = "Welcome to RENTA !", colors = MaterialTheme.colors.primaryVariant
        )
        SecondHeading(
            modifier= Modifier
                .offset(y = (getheight() * 0.01).dp)
                .align(Alignment.Start),
            symbol = "Sign Into Your Account",
            colors =  MaterialTheme.colors.onPrimary)

       var email= TextField(
            modifier= Modifier
                .width(width = (getwidth() * 0.80).dp)
                .offset(y = (getheight() * 0.05).dp),
            labels = "Phone",
            placeholders ="92+" )
       var password= TextField(
            modifier= Modifier
                .width(width = (getwidth() * 0.80).dp)
                .offset(y = (getheight() * 0.05).dp),
            labels = "Password",
            placeholders ="Password" )

        FilledButtonExample(
            modifier= Modifier
                .width(width = (getwidth() * 0.80).dp)
                .offset(y = (getheight() * 0.1).dp),
            onClick = {viewModel.signInExistingUser(navigator,email, password) },
            symbols = "Sign In" )
        TextBody(
            modifier= Modifier.offset(y = (getheight()*0.1).dp),
            colors = MaterialTheme.colors.onSecondary,
            symbol ="forgot Password" )
        TextBody(
            modifier= Modifier.offset(y = (getheight()*0.25).dp),
            colors = MaterialTheme.colors.onSecondary,
            symbol ="Or Continue With Social Account" )
        FilledButtonExample(
            modifier= Modifier
                .width(width = (getwidth() * 0.80).dp)
                .offset(y = (getheight() * 0.25).dp),
            onClick = {
//                lifecycleScope.launch {
//                    val signInIntentSender = googleAuthUiClient.signIn()
//                    launcher.launch(
//                        IntentSenderRequest.Builder(
//                            signInIntentSender ?: return@launch
//                        ).build()
//                    )
//                }
                signInLauncher.launch(googleSignInClient.signInIntent)

//                val firebaseAuth = FirebaseAuth.getInstance()

//                firebaseAuth.addAuthStateListener { auth ->
//                    val user = auth.currentUser
//                    print("heheh $user")
//                    if (user != null) {
//                      println("hehe succes")
//                        navigator.navigate(MainhomeDestination)
//                    } else {
//                        println("heheh fail")
//                    }
//                }
            },
            symbols = "Google" )

        Row {
            TextBody(
                modifier= Modifier.offset(y = (getheight()*0.25).dp),colors = MaterialTheme.colors.onSecondary,
                symbol ="Do not have an account" )
            Spacer(modifier = Modifier.width(width = (getwidth()*0.02).dp))

            TextBody(
                modifier = Modifier
                    .offset(y = (getheight() * 0.25).dp)
                    .clickable {
                        navigator.navigate(SignUpPageDestination)
                    },
                colors = Color.Blue,
                symbol = "Sign-Up",
            )
        }
    }
}

