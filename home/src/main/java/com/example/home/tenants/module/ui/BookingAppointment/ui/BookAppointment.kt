package com.example.home.tenants.module.ui.BookingAppointment.ui
import android.content.Context
import android.content.SharedPreferences
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.home.Recommendation.ui.Tenant.FavouriteButton
import com.example.home.Recommendation.ui.Tenant.saveTenantPostResponse
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.destinations.propertyScreenDestination
import com.example.home.destinations.propertyTenantScreenDestination
import com.example.home.tenants.module.ui.BookingAppointment.domain.FirebasePostResponse
import com.google.gson.Gson
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
    val viewModel: BookingAppointmentViewModel = viewModel()
    LaunchedEffect(true){
        viewModel.getBookings()
    }
    var statelazy = rememberLazyListState()

    val bookings by viewModel.bookings.collectAsState()
    val post=bookings
    println(" firebase $post")
    post?.let { LazyColumn (
        state = statelazy,
        modifier = Modifier
            .fillMaxSize()){
        items(post ?: emptyList()) { postResponse ->
           FirebaseHotelItem(postResponse,navigator)
        }
        items(post ?: emptyList()) { postResponse ->
            FirebaseHotelItem(postResponse,navigator)
        }
    }
    }
}
@Composable
fun FirebaseHotelItem(postResponse: FirebasePostResponse, navigator: DestinationsNavigator) {
 BookedHotelItem(postResponse = postResponse, navigator =navigator )
}
@Destination
@Composable
fun AppointmentContent(navigator: DestinationsNavigator) {
    val viewModel: BookingAppointmentViewModel = viewModel()
    LaunchedEffect(true){
        viewModel.getAppointment()
    }
    var statelazy = rememberLazyListState()

    val bookings by viewModel.appointments.collectAsState()
    val post = bookings
    println(" firebase $post")
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
                            saveTenantPostResponse(
                                key = "selected_Tenant_post_response",
                                postResponse = postResponse.TenantPostResponse,
                                context = context
                            )
                            navigator.navigate(propertyTenantScreenDestination)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box() {
                        Image(
                            painter = painterResource(id = R.drawable.home4),
                            contentDescription = null,
                            modifier = Modifier.requiredSize(120.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(modifier = Modifier.padding(start=120.dp, top = 10.dp)) {
                            androidx.compose.material.Text(text = "Appointment At: ${postResponse.time}", fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(4.dp))
                            androidx.compose.material.Text(
                                text = "Date ${postResponse.date}",
                                fontSize = 14.sp
                            )
                            if (postResponse.approved == "No") {
                                androidx.compose.material.Text(
                                    text = "Approved: ${postResponse.approved}",
                                    fontSize = 14.sp,
                                    color = Color.Red
                                )
                            } else {
                                androidx.compose.material.Text(
                                    text = "Approved: ${postResponse.approved}",
                                    fontSize = 14.sp,
                                    color = Color.Green
                                )
                            }
                            androidx.compose.material.Text(
                                text = "Property: ${postResponse.TenantPostResponse.location} in ${postResponse.TenantPostResponse.area}",
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

@Composable
fun BookedHotelItem(postResponse: FirebasePostResponse, navigator: DestinationsNavigator) {
    val context= LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.20f)
        .padding(16.dp)
        .clip(shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
        .clickable {
            saveFirebasePostResponse(
                key = "selected_post_response",
                postResponse = postResponse.postResponse,
                context = context
            )
            navigator.navigate(propertyScreenDestination)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box (){
            Image(
                painter = rememberAsyncImagePainter(postResponse.postResponse.imageLink),
                contentDescription = null,
                modifier = Modifier.requiredSize(125.dp),
                contentScale = ContentScale.FillBounds
            )
            Column(modifier = Modifier.padding(start=130.dp, top = 10.dp)) {
                androidx.compose.material.Text(text = postResponse.postResponse.hotel,
                    fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                androidx.compose.material.Text(
                    text = "Booked From: ${postResponse.startDate} To ${postResponse.endDate}",
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
                androidx.compose.material.Text(
                    text = "Price: $${postResponse.postResponse.price} per night",
                    fontSize = 14.sp
                )


            }
        }



    }
}
fun saveFirebasePostResponse(context: Context, key: String, postResponse: PostResponse) {
    val sharedPreferences: SharedPreferences = context
        .getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = gson.toJson(postResponse)
    sharedPreferences.edit().putString(key, json).apply()
}