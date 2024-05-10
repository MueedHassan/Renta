package com.example.home.tenants.module.ui.BookingAppointment
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun BookAppointmentScreen(navigator: DestinationsNavigator) {
   val (bookingSelected, setBookingSelected) = remember { mutableStateOf(false) }
    val (appointmentSelected, setAppointmentSelected) = remember { mutableStateOf(false) }

    Column {
        TopButtonsLayout(
            modifier = Modifier,
            bookingSelected = bookingSelected,
            appointmentSelected = appointmentSelected,
            onBookingClick = {
                setBookingSelected(true)
                setAppointmentSelected(false)
            },
        ) { setBookingSelected(false)
            setAppointmentSelected(true)
          }

        when {

            bookingSelected -> BookingContent(navigator)
            appointmentSelected -> AppointmentContent(navigator)
        }
    }
}

@Composable
fun TopButtonsLayout(
    modifier: Modifier = Modifier,
    bookingSelected: Boolean,
    appointmentSelected: Boolean,
    onBookingClick: () -> Unit,
    onAppointmentClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(
                modifier = modifier
                    .fillMaxWidth(0.5f)
                    .height(60.dp)
                    .shadow(8.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(
                        color =
                        if (bookingSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surface
                    )
                    .clickable {
                        onBookingClick()
                    }
            ) {
                Text(text = "Booking", modifier = Modifier.align(Alignment.Center))
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(8.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(
                        color =
                        if (appointmentSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surface
                    )
                    .clickable {
                        onAppointmentClick()
                    }
            ) {
                Text(text = "Appointment", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Destination
@Composable
fun BookingContent(navigator: DestinationsNavigator) {
    // Content for Booking
}

@Destination
@Composable
fun AppointmentContent(navigator: DestinationsNavigator) {
    // Content for Appointment
}
