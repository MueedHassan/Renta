package com.example.home.Recommendation.ui.ui
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
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.MaterialTheme
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.home.Auth.Components.component.FirstHeading
import com.example.home.Auth.Components.component.SecondHeading
import com.example.home.Auth.Components.component.getheight
import com.example.home.R
import com.example.home.Recommendation.ui.TouristRecommendationVm
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.destinations.BookingPageDestination
import com.example.home.destinations.propertyScreenDestination
import com.google.gson.Gson
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun TouristRecommendation(navigator:DestinationsNavigator) {
    var cityName:String by remember { mutableStateOf(String()) }
    var cityRange:String by remember { mutableStateOf(String()) }
    var cityLocation:String by remember { mutableStateOf(String()) }
    var post by rememberSaveable { mutableStateOf<List<PostResponse>?>(emptyList()) }
    val vm: TouristRecommendationVm = viewModel()
    var statelazy = rememberLazyListState()
    val navController = rememberNavController()
    var offset by remember { mutableStateOf(0f) }
    var key by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPref = remember { context.getSharedPreferences("selected_post_response", Context.MODE_PRIVATE) }
    LaunchedEffect(true){
        offset=0f
        post = vm.getrec(
            cityName = cityName,
            cityRange = cityRange,
            cityLocation = cityLocation
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
                               "-Nearest To All The  Tourist Spot In The City\n" +
                               "-Located in low Crime Rate area",
                       colors =  MaterialTheme.colors.onPrimary)
                   Text(
                       text = "Enter Your Name",
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
                       value = cityName,
                       onValueChange = {
                           cityName = it
                       },
                       label = { Text("Enter Your Name") },
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
                       value = cityRange,
                       onValueChange = {
                           cityRange = it
                       },
                       label = { Text("Affordable Range") },
                       modifier = Modifier.fillMaxWidth(0.8f)
                   )
                   Text(
                       text = "Enter Your Home City",
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
                       value = cityLocation,
                       onValueChange = {
                           cityLocation = it
                       },
                       label = { Text("Location") },
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
                                   cityName = cityName,
                                   cityRange = cityRange,
                                   cityLocation = cityLocation
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
               HotelItem(postResponse,navigator)
           }
           }
       }
   }
}
@Composable
fun HotelItem(postResponse: PostResponse,navigator: DestinationsNavigator) {
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
            savePostResponse(
                key = "selected_post_response",
                postResponse = postResponse,
                context = context
            )
            navigator.navigate(propertyScreenDestination)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box (){
            Image(
                painter = rememberAsyncImagePainter(postResponse.imageLink),
                contentDescription = null,
                modifier = Modifier.requiredSize(200.dp),
                contentScale = ContentScale.FillBounds
            )
            FavouriteButton(modifier=Modifier.padding(start = 10.dp,top=10.dp))

            Column(modifier = Modifier.padding(start=210.dp, top = 10.dp)) {
                Text(text = postResponse.hotel, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Location: ${postResponse.location}", fontSize = 14.sp)
                Text(text = "Price: $${postResponse.price}", fontSize = 14.sp)
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
fun savePostResponse(context: Context, key: String, postResponse: PostResponse) {
    val sharedPreferences: SharedPreferences = context
        .getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    val gson = Gson()
    val json = gson.toJson(postResponse)
    sharedPreferences.edit().putString(key, json).apply()
}
fun getPostResponse(context: Context, key: String): PostResponse? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    val json = sharedPreferences.getString(key, null)
    val gson = Gson()
    return gson.fromJson(json, PostResponse::class.java)
}

@Destination
@Composable
fun propertyScreen( navigator: DestinationsNavigator){
    val context= LocalContext.current
    val post:PostResponse?= getPostResponse(context=context, key ="selected_post_response")
    Box(modifier =Modifier.fillMaxSize().padding(start = 16.dp,end=16.dp,bottom=16.dp) )
         {
        Column (modifier = Modifier.align(Alignment.TopStart)){
            BackButton(navigator =navigator)
            Box(modifier = Modifier.clip(shape = RoundedCornerShape(10.dp))){
                if (post != null) {
                    Image(
                        painter = rememberImagePainter(post.imageLink),
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
                post.hotel.let { Text(text = it, style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )) }
            }
            Spacer(modifier = Modifier.height(4.dp))
            if (post != null) {
                Text(text = "Location: ${post.location}", style = TextStyle(fontSize = 16.sp))
            }
            if (post != null) {
                Text(text = "Crime Rate: ${post.crimeRate}", style = TextStyle(fontSize = 16.sp))
            }
            if (post != null) {
                Text(text = "Price: $${post.price}", style = TextStyle(fontSize = 16.sp))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Heading(text = "Details")
            Spacer(modifier = Modifier.height(8.dp))
            if (post != null) {
                DetailRow(label = "Host:", value = post.host)
            }
            if (post != null) {
                DetailRow(label = "Guests:", value = post.guests)
            }
            if (post != null) {
                DetailRow(label = "Bedrooms:", value = post.bedrooms)
            }
            if (post != null) {
                DetailRow(label = "Bathrooms:", value = post.bathrooms)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier=Modifier.background(color = androidx.compose.material3.MaterialTheme.colorScheme.outline))
            Spacer(modifier = Modifier.height(10.dp))
            if (post != null) {
                Column() {
                    Text(
                        text = "Review:",
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = post.review,
                        style = TextStyle(fontSize = 16.sp, color = Color.Black)
                    )
                }
            }

        }
        Box (modifier= Modifier
            .align(Alignment.BottomCenter)
            .clickable {
                navigator.navigate(
                    BookingPageDestination
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
            text = "Book Now",
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