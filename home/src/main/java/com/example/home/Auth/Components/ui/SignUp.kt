package com.example.home.Auth.Components.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import com.example.home.Auth.Components.component.FilledButtonExample
import com.example.home.Auth.Components.component.FirstHeading
import com.example.home.Auth.Components.component.SecondHeading
import com.example.home.Auth.Components.component.TextBody
import com.example.home.Auth.Components.component.getheight
import com.example.home.Auth.Components.component.getwidth
import com.example.home.destinations.SignInDestination
import com.example.home.destinations.SignUpPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SignUpPage(navigator: DestinationsNavigator) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var income by remember { mutableStateOf("") }
    val viewModel = viewModel<SignInViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FirstHeading(
            modifier= Modifier
                .offset(y = (getheight() * 0.01).dp)
                .align(Alignment.Start),
            symbol = "New To Renta !", colors = MaterialTheme.colors.primaryVariant
        )
        SecondHeading(
            modifier= Modifier
                .offset(y = (getheight() * 0.01).dp)
                .align(Alignment.Start),
            symbol = "Lets Create An Account",
            colors =  MaterialTheme.colors.onPrimary)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        OutlinedTextField(
            value = income,
            onValueChange = { income = it },
            label = { Text("Current Income in PKR") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        FilledButtonExample(
            modifier= Modifier
                .padding(top = 10.dp)
                .width(width = (getwidth() * 0.80).dp)
                .height(50.dp),
            onClick = {
               viewModel.SignInNewUser(navigator,email, password,income,name)
                navigator.navigate(SignInDestination)
                      },
            symbols = "Sign Up" )

        Row {
            TextBody(
                modifier= Modifier
                    .offset(y = (getheight()*0.25).dp),
                colors = MaterialTheme.colors.onSecondary,
                symbol ="Already Have An Account," )

            Spacer(modifier = Modifier.width(width = (getwidth()*0.02).dp))

            TextBody(
                modifier = Modifier
                    .offset(y = (getheight() * 0.25).dp)
                    .clickable {

                        navigator.navigate(
//                            OnboardingDestination
                            SignInDestination
                        )
                    },
                colors = Color.Blue,
                symbol = "Sign-In",
            )
        }
    }
}


