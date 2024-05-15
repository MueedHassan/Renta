package com.example.home.Search

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.MaterialTheme
import com.example.home.Auth.Components.component.FirstHeading
import com.example.home.Auth.Components.component.getheight
import com.example.home.R
import com.example.home.addnewproperty.ui.Components.ui.screens.WashroomBathroomButton
import com.example.home.landlord.ui.ListingViewModel
import com.example.home.tenants.module.ui.Home.domain.Property
import com.example.home.tenants.module.ui.Home.ui.PropertyHome
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun Search(navigator: DestinationsNavigator){
    var location:String? by remember { mutableStateOf(String()) }
    var city:String? by remember { mutableStateOf(String()) }
    var area:String? by remember { mutableStateOf(String()) }
    var Range:String? by remember { mutableStateOf(String()) }
    var post by rememberSaveable { mutableStateOf<List<Property>?>(emptyList()) }
    val vm: ListingViewModel= viewModel()
    var statelazy = rememberLazyListState()
    var offset by remember { mutableStateOf(0f) }
    var key by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val sharedPref = remember { context.getSharedPreferences("selected_post_response", Context.MODE_PRIVATE) }
    val rentaltype= listOf("Long Term Rental","Short Term Rental")
    val renterstatus= listOf("Only Family","All Boys","All Girls")
    var selectedstatus:String? by rememberSaveable {
        mutableStateOf("")
    }
    var selectedRenterstatus:String? by rememberSaveable {
        mutableStateOf("")
    }
    var maxpeople by rememberSaveable {
        mutableStateOf(" ")
    }
    var propertystatus by rememberSaveable {
        mutableStateOf(" ")
    }
    var propertyRenterstatus by rememberSaveable {
        mutableStateOf(" ")}
    LaunchedEffect(true){
        offset=0f
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ){value->
        offset=statelazy.firstVisibleItemScrollOffset.toFloat()
        val imageslist = listOf(
            R.drawable.home1,
            R.drawable.home3,
            R.drawable.home4,
            R.drawable.home6,
        )
        post?.let { LazyColumn (
            state = statelazy,
            modifier = Modifier
                .fillMaxSize()
                .padding(value)){
            item{
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
                        symbol = "Search", colors = MaterialTheme.colors.primaryVariant
                    )
                    Text(
                        text = "Enter Location",
                        color= MaterialTheme.colors.onPrimary,
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
                    location?.let { it1 ->
                        OutlinedTextField(
                            value = it1,
                            onValueChange = {
                                location = it
                            },
                            label = { Text("Location") },
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                    Text(
                        text = "City",
                        color= MaterialTheme.colors.onPrimary,
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
                    city?.let { it1 ->
                        OutlinedTextField(
                            value = it1,
                            onValueChange = {
                                city = it
                            },
                            label = { Text("City") },
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                    Text(
                        text = "Area",
                        color= MaterialTheme.colors.onPrimary,
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
                    area?.let { it1 ->
                        OutlinedTextField(
                            value = it1,
                            onValueChange = {
                                area = it
                            },
                            label = { Text("Area") },
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                    Text(
                        text = "Enter Your Affordable Range ",
                        color= MaterialTheme.colors.onPrimary,
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
                    Range?.let { it1 ->
                        OutlinedTextField(
                            value = it1,
                            onValueChange = {
                                Range = it
                            },
                            label = { Text("Affordable Range") },
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                    Text(
                        text = "Renter Status",
                        color= MaterialTheme.colors.onPrimary,
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
                    Column (modifier = Modifier)
                    {
                        LazyHorizontalGrid(rows = GridCells.Fixed(1),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp) ,
                            modifier = Modifier.height(45.dp)

                        ) {
                            items(renterstatus.size){item->
                                WashroomBathroomButton(
                                    text = renterstatus[item],
                                    isSelected = selectedRenterstatus==renterstatus[item],
                                    onSelected = {
                                            selected->
                                        selectedRenterstatus=renterstatus[item]
                                    }
                                )
                            }
                        }
                    }
                    Text(
                        text = "property Status",
                        color= MaterialTheme.colors.onPrimary,
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
                    Column (modifier = Modifier)
                    { LazyHorizontalGrid(rows = GridCells.Fixed(1),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp) ,
                        modifier = Modifier.height(45.dp)

                    ) {
                        items(rentaltype.size){item->
                            WashroomBathroomButton(
                                text = rentaltype[item],
                                isSelected = selectedstatus==rentaltype[item],
                                onSelected = {
                                        selected->
                                    selectedstatus=rentaltype[item]
                                }
                            )
                        }
                    }
                    }

                    Box(
                        modifier= Modifier
                            .fillMaxWidth(0.8f)
                            .height(48.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.primary)
                            .clickable {

                                post=vm.properties.value

//                                post = vm.getrec(
//                                    location = location,
//                                    city = city,
//                                    area = area,
//                                    range = Range
//                                )
//                                vm.newresponse = post
                            }
                    ){ Text(
                        text = "Recommendation",
                        modifier = Modifier.align(Alignment.Center),

                        )
                    }
                }

            }
            items(post!!.size) { item ->
                PropertyHome(
                    post = post!!,
                    imageslist = imageslist,
                    item = item, navigator =navigator
                )

            }
        }
        }
    }
}