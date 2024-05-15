package com.example.home.landlord.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.home.R
import com.example.home.destinations.AddNewPropertyDestination
import com.example.home.tenants.module.ui.Home.ui.PropertyHome
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun ListingScreen(navigator: DestinationsNavigator) {
    val vm:ListingViewModel= viewModel()
    val propertiesState = vm.properties.collectAsState()
    val post=propertiesState.value
    val landLordHomeScreenVm:LandLordHomeScreenVm= viewModel()
    val propertyCount=landLordHomeScreenVm.ownerIdCount.value
    val imageslist = listOf(
        R.drawable.home1,
        R.drawable.home3,
        R.drawable.home4,
        R.drawable.home6,
    )
    val lazyListState = rememberLazyListState()
    Column(modifier= Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        AddBox(
              navigator = navigator,
              modifier = Modifier
                  .align(Alignment.End)
                  .padding(top = 10.dp, end = 10.dp)
          )
        if (propertyCount==0){
            Text(
                text = "Nothing to Show",
                style=MaterialTheme.typography.bodyMedium,
                color=MaterialTheme.colorScheme.outline,
                modifier=Modifier.padding(top=2.dp,start=5.dp).align(Alignment.CenterHorizontally)
            )
        }
        else{
            Text(
                text = "Your Listing",
                style=MaterialTheme.typography.bodyLarge,
                color=MaterialTheme.colorScheme.outline,
                modifier=Modifier.padding(top=2.dp,start=5.dp).align(Alignment.CenterHorizontally)
            )
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()



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
fun AddBox(navigator: DestinationsNavigator,modifier: Modifier){
    Box(modifier = Modifier
        .then(modifier)
        .width(50.dp)
        .height(50.dp)
        .clip(shape = CircleShape)
        .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.6f))
        .clickable {
            navigator.navigate(AddNewPropertyDestination)
        }
    )
    {
        Image(painter = painterResource(id = R.drawable.add2), contentDescription =null,
            modifier=Modifier.align(Alignment.Center),
            contentScale = ContentScale.FillBounds,)
    }

}
