package com.example.home.landlord.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun LandLordHomeScreen(navigator: DestinationsNavigator){
    val vm:LandLordHomeScreenVm= viewModel()
    Column(modifier=Modifier.fillMaxSize().padding(10.dp)) {
       val userName = vm.userName.value
        val propertcount=vm.ownerIdCount.value
        val appointmentcount=vm.searchedAppointmentsCount.value
        if (userName.isNotEmpty()) {
            Text(text = "Welcome $userName",
                style=MaterialTheme.typography.displayLarge,
                color= MaterialTheme.colorScheme.outline,
                modifier=Modifier.padding(top=5.dp,start=5.dp)
            )
        } else {
            CircularProgressIndicator()
        }
        Text(text = "Number Of Property Listed ",
            style=MaterialTheme.typography.headlineLarge,
            color= MaterialTheme.colorScheme.outline,
            modifier=Modifier.padding(top=5.dp,start=5.dp)
        )
        if (propertcount.toString().isNotEmpty()) {
            Text(text = "${propertcount.toString()}",
                style=MaterialTheme.typography.headlineMedium,
                color= MaterialTheme.colorScheme.outline,
                modifier=Modifier.padding(top=5.dp,start=5.dp)
            )
        } else {
            CircularProgressIndicator()
        }
        Text(text = "Number Of Appointment",
            style=MaterialTheme.typography.headlineLarge,
            color= MaterialTheme.colorScheme.outline,
            modifier=Modifier.padding(top=5.dp,start=5.dp)
        )
        if (appointmentcount.toString().isNotEmpty()) {
            Text(text = "${appointmentcount.toString()}",
                style=MaterialTheme.typography.headlineMedium,
                color= MaterialTheme.colorScheme.outline,
                modifier=Modifier.padding(top=5.dp,start=5.dp)
            )
        } else {
            CircularProgressIndicator()
        }
        Text(text = "Number Of Booking",
            style=MaterialTheme.typography.headlineLarge,
            color= MaterialTheme.colorScheme.outline,
            modifier=Modifier.padding(top=5.dp,start=5.dp)
        )
        val bookingcount=vm.searchedBookingsCount.value
        if (bookingcount.toString().isNotEmpty()) {
            Text(text = "${bookingcount.toString()}",
                style=MaterialTheme.typography.headlineMedium,
                color= MaterialTheme.colorScheme.outline,
                modifier=Modifier.padding(top=5.dp,start=5.dp)
            )
        } else {
            CircularProgressIndicator()
        }

    }
}

