package com.example.home.landlord.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.home.R
import com.example.home.Recommendation.ui.Tenant.saveTenantPostResponse
import com.example.home.destinations.propertyScreenDestination
import com.example.home.destinations.propertyTenantScreenDestination
import com.example.home.landlord.domain.BookingProperty
import com.example.home.tenants.module.ui.BookingAppointment.domain.FirebasePostResponse
import com.example.home.tenants.module.ui.BookingAppointment.ui.saveFirebasePostResponse
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun Reservation(navigator: DestinationsNavigator) {
    val (bookingSelected, setBookingSelected) = remember { mutableStateOf(false) }
    val (appointmentSelected, setAppointmentSelected) = remember { mutableStateOf(false) }

    Column {
        TopButtonsLandlordLayout(
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
            bookingSelected -> BookingLandlordContent(navigator=navigator)
            appointmentSelected -> AppointmentLandlordContent(navigator=navigator)
        }
    }
}



    @Composable
    fun AppointmentLandlordContent(navigator: DestinationsNavigator) {
        val vm: ReservationViewModel = viewModel()
        var statelazy = rememberLazyListState()
        val bookings by vm.searchedAppointments.collectAsState()
        val post = bookings
        post?.let {
            LazyColumn(
                state = statelazy,
                modifier = Modifier.fillMaxSize()
            ) {
                items(post ?: emptyList()) { postResponse ->
                    val context = LocalContext.current
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.20f)
                            .padding(16.dp)
                            .clip(shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
                            .clickable {
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box() {
                            Image(
                                painter =rememberAsyncImagePainter(postResponse.image),
                                contentDescription = null,
                                modifier = Modifier.requiredSize(120.dp),
                                contentScale = ContentScale.FillBounds
                            )
                            Column(modifier = Modifier.padding(start=130.dp, top = 5.dp)) {
                                androidx.compose.material.Text(text = "Appointment At: ${postResponse.time}", fontSize = 18.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                androidx.compose.material.Text(
                                    text = "Date ${postResponse.date}",
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                androidx.compose.material.Text(
                                    text = "Guest Name:${postResponse.name}",
                                    fontSize = 14.sp
                                )

                                androidx.compose.material.Text(
                                    text = "Property: ${postResponse.title}",
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                                Row (){
                                    androidx.compose.material.Text(
                                        text = "Approve it",
                                        fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                    Spacer(modifier = Modifier.width(5.dp))
                                    androidx.compose.material.Text(
                                        text = "Yes",
                                        fontSize = 14.sp,
                                        color = Color.Green
                                    )
                                    Spacer(modifier = Modifier.width(20.dp))
                                    androidx.compose.material.Text(
                                        text = "No",
                                        fontSize = 14.sp,
                                        color = Color.Red
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun BookingLandlordContent(navigator: DestinationsNavigator) {
        val vm: ReservationViewModel = viewModel()
        var statelazy = rememberLazyListState()
        val bookings by vm.searchedBookings.collectAsState()
        val post = bookings
        post?.let {
            LazyColumn(
                state = statelazy,
                modifier = Modifier.fillMaxSize()
            ) {
                items(post ?: emptyList()) { postResponse ->
                    BookedHotelItem(postResponse=postResponse,navigator=navigator)
                }
            }
        }
    }



@Composable
fun BookedHotelItem(postResponse: BookingProperty, navigator: DestinationsNavigator) {
    val context= LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.20f)
        .padding(16.dp)
        .clip(shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
        .clickable {
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box (){
            Image(
                painter = rememberAsyncImagePainter(postResponse.image),
                contentDescription = null,
                modifier = Modifier.requiredSize(125.dp),
                contentScale = ContentScale.FillBounds
            )
            Column(modifier = Modifier.padding(start=130.dp, top = 5.dp)) {
                androidx.compose.material.Text(text = postResponse.title,
                    fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                androidx.compose.material.Text(
                    text = "Booked From: ${postResponse.startdate} To ${postResponse.enddate}",
                    fontSize = 16.sp
                )
                androidx.compose.material.Text(
                    text = "Adults: ${postResponse.adults}",
                    fontSize = 14.sp
                )
                androidx.compose.material.Text(
                    text = "Children: ${postResponse.children}",
                    fontSize = 14.sp
                )
                Row (){
                    androidx.compose.material.Text(
                        text = "Approve it",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    androidx.compose.material.Text(
                        text = "Yes",
                        fontSize = 14.sp,
                        color = Color.Green
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    androidx.compose.material.Text(
                        text = "No",
                        fontSize = 14.sp,
                        color = Color.Red
                    )
                }


            }
        }



    }
}
@Composable
fun TopButtonsLandlordLayout(
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
