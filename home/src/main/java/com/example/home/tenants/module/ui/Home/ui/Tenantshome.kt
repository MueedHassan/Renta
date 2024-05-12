package com.example.home.tenants.module.ui.Home.ui
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.home.AppBarExpendable
import com.example.home.AppBarShrinked
import com.example.home.Appointment.AppointmentViewModel
import com.example.home.R
import com.example.home.Recommendation.ui.Tourist.BackButton
import com.example.home.Recommendation.ui.Tourist.DetailRow
import com.example.home.Recommendation.ui.Tourist.FavouriteButton
import com.example.home.Recommendation.ui.Tourist.Heading
import com.example.home.Recommendation.ui.Tourist.TouristRecommendationVm
import com.example.home.Recommendation.ui.Tourist.getPostResponse
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.Recommendation.ui.remote.data.TenantPostResponse
import com.example.home.Recommendation.ui.ui.BookingViewModel
import com.example.home.destinations.AddNewPropertyDestination
import com.example.home.destinations.BookingPageDestination
import com.example.home.destinations.DateTimePickerComponentDestination
import com.example.home.destinations.LandlordHomeDestination
import com.example.home.destinations.MainhomeDestination
import com.example.home.destinations.PropertyAppointmentDestination
import com.example.home.destinations.PropertyHomeDescriptionDestination
import com.example.home.destinations.propertySearchedBookDestination
import com.example.home.tenants.module.ui.Home.domain.Property
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TenantHome(navigator: DestinationsNavigator) {

    var post by rememberSaveable { mutableStateOf<List<Property>?>(emptyList()) }
    var TenantHomeViewModel:TenantHomeViewModel= viewModel()
    val propertiesState = remember { TenantHomeViewModel.properties }
    LaunchedEffect(true){
        TenantHomeViewModel.getProperties()

    }
    val properties: State<List<Property>> = TenantHomeViewModel.properties.collectAsState()
    val lazyListState = rememberLazyListState()
    var offset by remember { mutableStateOf(0f) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val vm: TouristRecommendationVm = viewModel()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (modifier=Modifier.fillMaxWidth(0.7f)){
                Text("Renta", modifier = Modifier.padding(top=20.dp,start=16.dp))
                Button(
                    onClick = { navigator.navigate(LandlordHomeDestination) },
                    colors= ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor =MaterialTheme.colorScheme.inverseOnSurface
                    ),
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .clip(shape = RoundedCornerShape(1.dp))
                        .height(40.dp)
                        .fillMaxWidth(0.80f)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            navigator.apply {
                                popBackStack()
                                navigate(LandlordHomeDestination)
                            }
                        }
                    ,
                ){
                    Text(
                        text =" Switch To Host/Landlord",
                        style=MaterialTheme.typography.bodyLarge

                    )
                }
                NavigationDrawerItem(
                    label = { Text(text ="Add New Property") },
                    selected = false,
                    onClick = {
                        navigator.navigate(
                            AddNewPropertyDestination(),false
                        )
                        /*TODO*/},
                ) }
        },
    ) {Scaffold(
        topBar = {
            if (offset.toInt()!=0) {
                AppBarShrinked()
            } else {

                AppBarExpendable(drawerstate = drawerState, scope = scope)
            }
        },

        ) { values ->
         offset = lazyListState.firstVisibleItemIndex.toFloat()
         post=properties.value

        println("property $properties")
        val imageslist = listOf(
            R.drawable.home1,
            R.drawable.home3,
            R.drawable.home4,
            R.drawable.home6,
        )
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(values)


        ) {
            items(post!!.size) { item ->
                PropertyHome(
                    post = post!!,
                    imageslist = imageslist,
                    item = item, navigator = navigator
                )
            }
        }
    }
    }
}
@Composable
fun FavouritePropertyButton(modifier: Modifier) {
    var isFavourite by remember { mutableStateOf(false) }
//    val viewModel: TenantRecommendationVm = viewModel()
    Box(
        modifier = Modifier
            .then(modifier)
            .size(56.dp)
            .clip(CircleShape)
            .background(color = Color.White)
            .clickable {
                isFavourite = !isFavourite
//                if (isFavourite) {
//                    viewModel.addToFavourites(postResponse)
//                } else {
//                    viewModel.removeFromFavourites(postResponse)
//                }
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = null,
            tint= Color.Red

        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PropertyHome(post: List<Property>,
                 imageslist: List<Int>,
                 item: Int,
                 navigator: DestinationsNavigator) {
        var context= LocalContext.current
            val pagerState = rememberPagerState(pageCount = {
                if (post!![item].List_Of_Images_Uris.isNotEmpty())
                {
                    post!![item].List_Of_Images_Uris.size
                }
                else
                {
                    imageslist.size
                }
            })
            Column(
                modifier= Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .clickable {
                        savePropertyPostResponse(
                            context = context,
                            postResponse = post[item],
                            key = "property"
                        )
                        navigator.navigate(PropertyHomeDescriptionDestination)
                    }
            )
            { HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(),
                    pageSize = PageSize.Fill
                ) { page ->
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp))
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .align(Alignment.Center),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                        if(post!![item].List_Of_Images_Uris.isNotEmpty()) {
                            Image(
                                painter =  rememberAsyncImagePainter( post!![item].List_Of_Images_Uris[page]), contentDescription = null,
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .fillMaxWidth()
                                    .height(350.dp)
                                    .align(Alignment.TopCenter),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                        else
                        { Image(
                            painter =  painterResource(id = imageslist[page]), contentDescription = null,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(15.dp))
                                .fillMaxWidth()
                                .height(350.dp)
                                .align(Alignment.TopCenter),
                            contentScale = ContentScale.FillBounds
                        )
                        }
                        Row(
                            Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color = if (pagerState.currentPage == iteration)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.primary.copy(alpha=0.5f)
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(RectangleShape)
                                        .background(color)
                                        .size(12.dp)
                                )
                            }
                        }
                        FavouritePropertyButton(modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp))
                    }
                }
                Text(
                    text = post!![item].Title.toString(),
                    style=MaterialTheme.typography.headlineLarge,
                    color=MaterialTheme.colorScheme.outline,
                    modifier=Modifier.padding(top=2.dp,start=5.dp)
                )
                Text(
                    text ="Location: ${post!![item].Location.toString()}," +
                            "${post!![item].City.toString()}",
                    style=MaterialTheme.typography.headlineMedium,
                    color=MaterialTheme.colorScheme.outline,
                    modifier=Modifier.padding(top=5.dp,start=5.dp)
                )
                Text(
                    text ="Property Status: ${post!![item].PropertyStatus.toString()}",
                    style=MaterialTheme.typography.bodyMedium,
                    color=MaterialTheme.colorScheme.outline,
                    modifier=Modifier.padding(top=5.dp,start=5.dp)
                )
                if(post!![item].PropertyStatus=="Long Term Rental")
                { Text(
                        text ="Prize:RS ${post!![item].Price.toString()} Per Month",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp),
                        fontStyle = FontStyle.Italic
                    )
                }
                else{
                    Text(
                        text ="Prize:RS ${post!![item].Price.toString()} Per Night",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp,bottom =10.dp),
                        fontStyle = FontStyle.Italic
                    )
                }
            }
    }
@OptIn(ExperimentalFoundationApi::class)
@Composable
@Destination
fun PropertyHomeDescription(navigator: DestinationsNavigator)
{   val context= LocalContext.current
    val post= getPropertyPostResponse(context =context, key= "property" )
    val imageslist = listOf(
        R.drawable.home1,
        R.drawable.home3,
        R.drawable.home4,
        R.drawable.home6,
    )
    val pagerState = rememberPagerState(pageCount = {
        if (post?.List_Of_Images_Uris?.isNotEmpty() == true)
        {
            post.List_Of_Images_Uris.size
        }
        else
        {
            imageslist.size
        }
    })


    Box(
        modifier=Modifier.fillMaxSize()
    ){
        LazyColumn(modifier = Modifier.align(Alignment.TopStart)){
            item {
                Column(
                    modifier= Modifier
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(10.dp))

                    )
                { HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxSize(),
                    pageSize = PageSize.Fill
                ) { page ->
                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp))
                    ){
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp)
                                .align(Alignment.Center),
                            color = MaterialTheme.colorScheme.primary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                        if(post!!.List_Of_Images_Uris.isNotEmpty()) {
                            Image(
                                painter =  rememberAsyncImagePainter( post.List_Of_Images_Uris[page]), contentDescription = null,
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(15.dp))
                                    .fillMaxWidth()
                                    .height(350.dp)
                                    .align(Alignment.TopCenter),
                                contentScale = ContentScale.FillBounds
                            )
                        }
                        else
                        { Image(
                            painter =  painterResource(id = imageslist[page]), contentDescription = null,
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(15.dp))
                                .fillMaxWidth()
                                .height(350.dp)
                                .align(Alignment.TopCenter),
                            contentScale = ContentScale.FillBounds
                        )
                        }
                        Row(
                            Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 20.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color = if (pagerState.currentPage == iteration)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.primary.copy(alpha=0.5f)
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .clip(RectangleShape)
                                        .background(color)
                                        .size(12.dp)
                                )
                            }
                        }
                        FavouritePropertyButton(modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 10.dp, end = 10.dp))
                    }
                }
                    Text(
                        text = post!!.Title.toString(),
                        style=MaterialTheme.typography.headlineLarge,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=2.dp,start=5.dp)
                    )
                    Text(
                        text ="Location: ${post!!.Location.toString()}," +
                                "${post!!.City.toString()}",
                        style=MaterialTheme.typography.headlineMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    Divider(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                    )
                    Text(
                        text ="Description:\n ${post!!.Descrition.toString()} Per Night",
                        style=MaterialTheme.typography.bodyLarge,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp,bottom =10.dp),
                        fontStyle = FontStyle.Italic
                    )

                    Divider(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                    )
                    Text(
                        text ="Place Type: ${post!!.PlaceType.toString()}",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    Text(
                        text ="Property Status: ${post!!.PropertyStatus.toString()}",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    Text(
                        text ="Renter Status Allowed: ${post!!.RenterStatus.toString()}",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    if(post!!.PropertyStatus=="Long Term Rental")
                    { Text(
                        text ="Prize:RS ${post!!.Price.toString()} Per Month",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp),
                        fontStyle = FontStyle.Italic
                    )
                    }
                    else{
                        Text(
                            text ="Prize:RS ${post!!.Price.toString()} Per Night",
                            style=MaterialTheme.typography.bodyMedium,
                            color=MaterialTheme.colorScheme.outline,
                            modifier=Modifier.padding(top=5.dp,start=5.dp,bottom =10.dp),
                            fontStyle = FontStyle.Italic
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                    )
                    Text(
                        text ="Bathroom: ${post!!.Washroom.toString()}",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    Text(
                        text ="Bedroom: ${post!!.Bedroom.toString()}",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    Text(
                        text ="Max Occupancy: ${post!!.MaxOccupancy.toString()}",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    Text(
                        text ="Area: ${post!!.Area.toString()} sq.feet",
                        style=MaterialTheme.typography.bodyMedium,
                        color=MaterialTheme.colorScheme.outline,
                        modifier=Modifier.padding(top=5.dp,start=5.dp)
                    )
                    if(post!!.PropertyStatus=="Long Term Rental"){
                        androidx.compose.material3.Button(
                            onClick = {
//
                     navigator.navigate(PropertyAppointmentDestination)
                            },
                            modifier = Modifier
                                .padding(bottom = 20.dp ,top = 10.dp,start=80.dp)
                                .fillMaxWidth(0.8f).height(48.dp),
                        ) {
                            Text(text = "Book Appointment")
                        }}
                    else {

                        androidx.compose.material3.Button(
                            onClick = {
                      navigator.navigate(propertySearchedBookDestination)
                            },
                            modifier = Modifier
                                .padding(bottom = 20.dp, top = 10.dp,start=80.dp)
                                .fillMaxWidth(0.8f).height(48.dp)

                        ) {
                            Text(text = "Book Property")
                        }
                    }

                } }

        }
    }

}

fun savePropertyPostResponse(context: Context, key: String, postResponse:Property ) {
    val sharedPreferences: SharedPreferences = context
        .getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = gson.toJson(postResponse)
    sharedPreferences.edit().putString(key, json).apply()
}
fun getPropertyPostResponse(context: Context, key: String): Property? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    val json = sharedPreferences.getString(key, null)
    val gson = Gson()
    return gson.fromJson(json, Property::class.java)
}
@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun PropertyAppointment(navigator: DestinationsNavigator) {
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState()
    var showTimePicker by remember { mutableStateOf(false) }
    var name by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var time by rememberSaveable { mutableStateOf("") }
    val vm: TenantHomeViewModel = viewModel()
    var key by remember { mutableStateOf(false) }
    val context= LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,

            ) {
            Text(text="Booking",
                style=MaterialTheme.typography.headlineMedium,
                color=MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom=10.dp)
            )


            Text(text="Number Of Adults",
                style=MaterialTheme.typography.bodyMedium,
                color=MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom=5.dp)
            )
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth()
            )
            androidx.compose.material.Divider(modifier = Modifier.padding(vertical = 24.dp))
            Text(text="Select Appointment date",
                style=MaterialTheme.typography.bodyMedium,
                color=MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom=5.dp)
            )

            Text(text = if (date=="")"No Date Selected" else date, modifier = Modifier.padding(bottom = 16.dp))

            androidx.compose.material3.Button(
                onClick = {
                    showDatePicker = true
                    date = vm.formatDate(datePickerState.selectedDateMillis).toString()
                    key = true
                },
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .height(35.dp),
            ) {
                Text(text = "Date Picker")
            }

            androidx.compose.material.Divider(modifier = Modifier.padding(vertical = 24.dp))
            Text(text="Select Appointment Time",
                style=MaterialTheme.typography.bodyMedium,
                color=MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom=5.dp)
            )
            Text(text = if (time=="")"No Time Selected" else time, modifier = Modifier.padding(bottom = 16.dp))

            androidx.compose.material3.Button(
                onClick = {
                    showTimePicker = true
                    time = vm.formatTime(timePickerState.hour, timePickerState.minute)
                    key = true

                },
                modifier = Modifier
                    .fillMaxWidth(0.45f)
                    .height(35.dp),
            ) {
                Text(text = "Time Picker")
            }

        }
        androidx.compose.material3.Button(
            onClick = {
                vm.viewModelScope.launch {
                 vm.storeAppointment(name = name, time = time, date = date, context = context)
                }
                navigator.navigate(MainhomeDestination)
            },
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.8f).height(48.dp)
                .align(Alignment.BottomCenter),
        ) {
            Text(text = "Book Appointment")
        }

    }

    if (showDatePicker) {

        DatePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            DatePicker(state = datePickerState)
        }
        date= vm.formatDate(datePickerState.selectedDateMillis).toString()

    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) { Text("Cancel") }
            }
        )
        {
            TimePicker(state = timePickerState)
        }
        time=vm.formatTime(timePickerState.hour,timePickerState.minute)
    }

}
@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun  propertySearchedBook(navigator: DestinationsNavigator) {
    var adults by rememberSaveable { mutableStateOf(1) }
    var children by rememberSaveable { mutableStateOf(0) }
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()
    SnackbarHost(hostState = snackState, Modifier.zIndex(1f))
    val state = rememberDateRangePickerState()
    val vm: TenantHomeViewModel = viewModel()
    var startDateFormatted by remember { mutableStateOf("") }
    var endDateFormatted by remember { mutableStateOf("") }
    var isCLicked by remember { mutableStateOf(false) }
    val context=LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Booking",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        androidx.compose.material.Divider(
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.outline
            )
        )
        Text(
            text = "Select Duration Of Stay",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(100.dp)
                )
                .fillMaxWidth(0.40f)
                .height(40.dp)
                .padding(top = 10.dp)
                .clickable {
                    isCLicked = !isCLicked
                }
        ) {
            Text(
                text = "Select Date",
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (isCLicked) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DatePickerDefaults.colors().containerColor)
                        .padding(start = 12.dp, end = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { isCLicked = false }) {
                        Icon(Icons.Filled.Close, contentDescription = "Localized description")
                    }
                    TextButton(
                        onClick = {
                            snackScope.launch {
                                val range = state.selectedStartDateMillis!!..state.selectedEndDateMillis!!
                                snackState.showSnackbar("Saved range (timestamps): $range")
                                val startDateMillis = range.first
                                val endDateMillis = range.last
                                startDateFormatted = formatMillisToDate(startDateMillis)
                                endDateFormatted = formatMillisToDate(endDateMillis)
                                if(startDateFormatted=="No Date Selected")
                                {

                                    snackState.showSnackbar("Select Proper Date")

                                }
                                else
                                { snackState.showSnackbar("Saved range (timestamps): $startDateFormatted and $endDateFormatted")

                                }
                                isCLicked = false
                            }
                        },
                        enabled = state.selectedEndDateMillis != null
                    ) {
                        Text(text = "Save")
                    }
                }
                DateRangePicker(state = state, modifier = Modifier.weight(1f))
            }
        }
        androidx.compose.material.Divider(modifier = Modifier.background(color = MaterialTheme.colorScheme.outline))
        Text(
            text = "Number Of Adults",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        OutlinedTextField(
            value = adults.toString(),
            onValueChange = { adults = it.toIntOrNull() ?: 0 },
            label = { Text("Adults") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        androidx.compose.material.Divider(modifier = Modifier.background(color = MaterialTheme.colorScheme.outline))
        Text(
            text = "Number Of Children",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        OutlinedTextField(
            value = children.toString(),
            onValueChange = { children = it.toIntOrNull() ?: 0 },
            label = { Text("Children") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        androidx.compose.material3.Button(
            onClick = {
                vm.viewModelScope.launch {
                    vm.bookHotel(
                        adults = adults,
                        children = children,
                        startdate = startDateFormatted,
                        enddate = endDateFormatted,
                        context = context
                    )
                }
                navigator.navigate(MainhomeDestination)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Confirm Booking")
        }
    }
}
fun formatMillisToDate(millis: Long?): String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val selectedDate = java.util.Calendar.getInstance().apply {
        timeInMillis =millis ?: System.currentTimeMillis()
    }
    return if (selectedDate.after(java.util.Calendar.getInstance())) {
        dateFormatter.format(selectedDate.time)
    } else {
        "No Date Selected"
    }
}
