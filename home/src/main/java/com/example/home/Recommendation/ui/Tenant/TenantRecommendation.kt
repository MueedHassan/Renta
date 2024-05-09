package com.example.home.Recommendation.ui.Tenant
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import com.example.home.Auth.Components.component.FirstHeading
import com.example.home.Auth.Components.component.SecondHeading
import com.example.home.Auth.Components.component.getheight
import com.example.home.R
import com.example.home.Recommendation.ui.remote.data.TenantPostResponse
import com.example.home.destinations.BookingPageDestination
import com.example.home.destinations.DateTimePickerComponentDestination
import com.example.home.destinations.propertyTenantScreenDestination
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TenantRecommendation(navigator:DestinationsNavigator) {
    var Loc1:String by remember { mutableStateOf(String()) }
    var Loc2:String by remember { mutableStateOf(String()) }
    var Loc3:String by remember { mutableStateOf(String()) }
    var Range:String by remember { mutableStateOf(String()) }
    var post by rememberSaveable { mutableStateOf<List<TenantPostResponse>?>(emptyList()) }
    val vm: TenantRecommendationVm = viewModel()
    var statelazy = rememberLazyListState()
    var offset by remember { mutableStateOf(0f) }
    var key by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPref = remember { context.getSharedPreferences("selected_post_response", Context.MODE_PRIVATE) }
    LaunchedEffect(true){
        offset=0f
        post = vm.getrec(
            loc1=Loc1,
            loc2=Loc2,
            loc3=Loc3,
            range=Range
        )
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            if(offset.toInt() ==0)
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp))
                {
                    FirstHeading(
                        modifier= Modifier
                            .offset(y = (getheight() * 0.01).dp)
                            .align(Alignment.Start),
                        symbol = "We are Here To Help ", colors = MaterialTheme.colors.primaryVariant
                    )
                    SecondHeading(
                        modifier= Modifier
                            .offset(y = (getheight() * 0.01).dp)
                            .align(Alignment.Start),
                        symbol = "We Provide You Recommendation That Are \n" +
                                "-Nearest To your Prefered Location\n" +
                                "-Lies Is your Budget",
                        colors =  MaterialTheme.colors.onPrimary)
                    Text(
                        text = "Enter Your Most Visied Location",
                        color= MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(
                                500
                            )),
                        textAlign = TextAlign.Center,
                        modifier= Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.Start)
                    )
                    Divider(color = MaterialTheme.colors.onPrimary)
                    OutlinedTextField(
                        value =Loc1,
                        onValueChange = {
                            Loc1 = it
                        },
                        label = { Text("First Location") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Text(
                        text = "Enter Your Second Most Visied Location",
                        color= MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(
                                500
                            )),
                        textAlign = TextAlign.Center,
                        modifier= Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.Start)
                    )
                    Divider(color = MaterialTheme.colors.onPrimary)
                    OutlinedTextField(
                        value =Loc2,
                        onValueChange = {
                            Loc2 = it
                        },
                        label = { Text("Second Location") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Text(
                        text = "Enter Your Third Most  Visied Location",
                        color= MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(
                                500
                            )),
                        textAlign = TextAlign.Center,
                        modifier= Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.Start)
                    )
                    Divider(color = MaterialTheme.colors.onPrimary)
                    OutlinedTextField(
                        value =Loc3,
                        onValueChange = {
                            Loc3 = it
                        },
                        label = { Text("Third Location") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Text(
                        text = "Enter Your Affordable Range ",
                        color=MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(
                                500
                            )
                        ),
                        textAlign = TextAlign.Center,
                        modifier= Modifier
                            .padding(top = 10.dp)
                            .align(Alignment.Start)
                    )
                    Divider(color = MaterialTheme.colors.onPrimary)
                    OutlinedTextField(
                        value = Range,
                        onValueChange = {
                            Range = it
                        },
                        label = { Text("Affordable Range") },
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )

                    Box(
                        modifier= Modifier
                            .fillMaxWidth(0.8f)
                            .height(48.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = MaterialTheme.colors.primary)
                            .clickable {
                                post = vm.getrec(
                                    loc1 = Loc1,
                                    loc2 = Loc2,
                                    loc3 = Loc3,
                                    range = Range
                                )
                                vm.newresponse = post
                            }
                    ){ Text(
                        text = "Recommendation",
                        modifier = Modifier.align(Alignment.Center),

                        )
                    }
                }
            }
            else
            {
            }
        },
    ){value->
        offset=statelazy.firstVisibleItemScrollOffset.toFloat()
        post?.let { LazyColumn (
            state = statelazy,
            modifier = Modifier
                .fillMaxSize()
                .padding(value)){
            items(post ?: emptyList()) { postResponse ->
                TenantItem(postResponse,navigator)
            }
        }
        }
    }
}
@Composable
fun TenantItem(postResponse: TenantPostResponse,navigator: DestinationsNavigator) {
    var crimeRateColor:Color= Color.Black
    if (postResponse.crimeRate=="Low"){
        crimeRateColor= Color.Green
    }
    if (postResponse.crimeRate=="High"){
        crimeRateColor= Color.Red
    }
    if (postResponse.crimeRate=="medium"){
        crimeRateColor= Color.Blue
    }
    val context=LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.40f)
        .padding(16.dp)
        .clip(shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
        .clickable {
            saveTenantPostResponse(
                key = "selected_Tenant_post_response",
                postResponse = postResponse,
                context = context
            )
            navigator.navigate(propertyTenantScreenDestination)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box (){
            Image(
                painter = painterResource(id = R.drawable.home4),
                contentDescription = null,
                modifier = Modifier.requiredSize(200.dp),
                contentScale = ContentScale.FillBounds
            )
            FavouriteButton(modifier=Modifier.padding(start = 10.dp,top=10.dp))

            Column(modifier = Modifier.padding(start=210.dp, top = 10.dp)) {
                Text(text = postResponse.area, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Location: ${postResponse.location}", fontSize = 14.sp)
                Text(text = "Price: $${postResponse.rent}", fontSize = 14.sp)
                Text(text = "Bathroom: $${postResponse.bathrooms}", fontSize = 14.sp)
                Text(text = "BedRooms: $${postResponse.bedrooms}", fontSize = 14.sp)
                Text(text = "Area: $${postResponse.marla}", fontSize = 14.sp)
                Text(text = "Crime: ${postResponse.crimeRate}", fontSize = 14.sp,
                    color = crimeRateColor
                )

            }
        }
    }
}


@Composable
fun FavouriteButton(modifier: Modifier) {var isFavourite by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .then(modifier)
            .size(56.dp)
            .clip(CircleShape)
            .background(color = Color.White)
            .clickable {
                isFavourite = !isFavourite
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
@Composable
fun BackButton(navigator: DestinationsNavigator) {var isFavourite by remember { mutableStateOf(false) }
    Icon(
        painter = painterResource(id = R.drawable.backarrow),
        contentDescription = null,
        tint= Color.Black,
        modifier = Modifier
            .size(40.dp)
            .padding(bottom = 10.dp)

    )

}
fun saveTenantPostResponse(context: Context, key: String, postResponse: TenantPostResponse) {
    val sharedPreferences: SharedPreferences = context
        .getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = gson.toJson(postResponse)
    sharedPreferences.edit().putString(key, json).apply()
}
fun getTenantPostResponse(context: Context, key: String): TenantPostResponse? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    val json = sharedPreferences.getString(key, null)
    val gson = Gson()
    return gson.fromJson(json, TenantPostResponse::class.java)
}

@Destination
@Composable
fun propertyTenantScreen( navigator: DestinationsNavigator){
    val context= LocalContext.current
    val post:TenantPostResponse?= getTenantPostResponse(context=context, key ="selected_Tenant_post_response")
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp) )
    {
        Column (modifier = Modifier.align(Alignment.TopStart)){
            BackButton(navigator =navigator)
            Box(modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))){
                if (post != null) {
                    Image(
                        painter = painterResource(id = R.drawable.home1),
                        contentDescription = "Hotel Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.4f),
                        contentScale = ContentScale.FillBounds
                    )
                }
                FavouriteButton(modifier=Modifier.padding(start = 10.dp,top=10.dp))
            }
            Spacer(modifier = Modifier.height(4.dp))
            if (post != null) {
                post.location.let { Text(text = it, style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )) }
            }
            Spacer(modifier = Modifier.height(4.dp))
            if (post != null) {
                Text(text = "Location: ${post.area}", style = TextStyle(fontSize = 16.sp))
            }
            if (post != null) {
                Text(text = "Crime Rate: ${post.crimeRate}", style = TextStyle(fontSize = 16.sp))
            }
            if (post != null) {
                Text(text = "Price: $${post.rent}", style = TextStyle(fontSize = 16.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Heading(text = "Details")
            Spacer(modifier = Modifier.height(8.dp))
            if (post != null) {
                DetailRow(label = "Bathroom:", value = post.bathrooms.toString())
            }
            if (post != null) {
                DetailRow(label = "Bedrooms", value = post.bedrooms.toString())
            }
            if (post != null) {
                DetailRow(label = "Area", value = post.marla.toString())
            }

            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier=Modifier.background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline))
            Spacer(modifier = Modifier.height(10.dp))

        }
        Box (modifier= Modifier
            .align(Alignment.BottomCenter)
            .clickable {
                navigator.navigate(
                    DateTimePickerComponentDestination
                )
            }
            .padding(top = 10.dp)
            .height(50.dp)
            .fillMaxWidth(0.8f)
            .background(
                color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(10.dp)

            ))
        {  Text(
            text = "Book Appointment Now",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White),
            modifier=Modifier.align(Alignment.Center))

        }
    }


}

@Composable
fun Heading(text: String) {
    Text(
        text = text,
        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    )
}
@Composable
fun DetailRow(label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = label,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = TextStyle(fontSize = 16.sp, color = Color.Black)
        )
    }
}