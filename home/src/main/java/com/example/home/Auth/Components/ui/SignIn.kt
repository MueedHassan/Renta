package com.example.home.Auth.Components.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import com.example.home.Auth.Components.component.FilledButtonExample
import com.example.home.Auth.Components.component.FirstHeading
import com.example.home.Auth.Components.component.SecondHeading
import com.example.home.Auth.Components.component.TextBody
import com.example.home.Auth.Components.component.TextField
import com.example.home.Auth.Components.component.getheight
import com.example.home.Auth.Components.component.getwidth
import com.example.home.Mainhome
import com.example.home.destinations.MainhomeDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start=true)
@Composable
fun SignIn(
    navigator:DestinationsNavigator
)
{ Column (
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

        TextField(
            modifier= Modifier
                .width(width = (getwidth() * 0.80).dp)
                .offset(y = (getheight() * 0.05).dp),
            labels = "Phone",
            placeholders ="92+" )
        TextField(
            modifier= Modifier
                .width(width = (getwidth() * 0.80).dp)
                .offset(y = (getheight() * 0.05).dp),
            labels = "Password",
            placeholders ="Password" )

        FilledButtonExample(
            modifier= Modifier
                .width(width = (getwidth() * 0.80).dp)
                .offset(y = (getheight() * 0.1).dp),
            onClick = { navigator.navigate(MainhomeDestination)},
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

            },
            symbols = "Google" )

        Row {
            TextBody(
                modifier= Modifier.offset(y = (getheight()*0.25).dp),colors = MaterialTheme.colors.onSecondary,
                symbol ="Do not have an account" )
            Spacer(modifier = Modifier.width(width = (getwidth()*0.02).dp))

            TextBody(
                modifier= Modifier
                    .offset(y = (getheight() * 0.25).dp)
                ,
                colors = Color.Blue,
                symbol ="Sign-Up" ,
                )
        }
    }
}