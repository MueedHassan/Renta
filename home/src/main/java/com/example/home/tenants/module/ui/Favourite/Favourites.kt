package com.example.home.tenants.module.ui.Favourite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.home.Recommendation.ui.Tenant.TenantItem
import com.example.home.Recommendation.ui.Tourist.HotelItem
import com.example.home.Recommendation.ui.remote.data.PostResponse
import com.example.home.Recommendation.ui.remote.data.TenantPostResponse
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavouritesScreen(navigator: DestinationsNavigator) {
    val navController = rememberNavController()
    val (hotelSelected, setHotelSelected) = remember { mutableStateOf(false) }
    val (homeSelected, setHomeSelected) = remember { mutableStateOf(false) }

    Column {
        TopButtonsLayout(
            modifier = Modifier,
            hotelSelected = hotelSelected,
            homeSelected = homeSelected,
            onHotelClick = {
                setHotelSelected(true)
                setHomeSelected(false)
            }
        ) {
            setHotelSelected(false)
            setHomeSelected(true)
        }

        when {
            hotelSelected -> HotelContent(navigator)
            homeSelected -> HomeContent(navigator)
        }
    }
}

@Composable
fun TopButtonsLayout(
    modifier: Modifier = Modifier,
    hotelSelected: Boolean,
    homeSelected: Boolean,
    onHotelClick: () -> Unit,
    onHomeClick: () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Box(
                modifier = modifier
                    .fillMaxWidth(0.5f)
                    .height(60.dp)
                    .shadow(8.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(color = if (hotelSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface)
                    .clickable {
                        onHotelClick()
                    }
            ) {
                Text(text = "Hotel",
                    modifier=Modifier.align(Alignment.Center)
                )
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .shadow(8.dp)
                    .clip(shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(
                        color =
                        if (homeSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surface
                    )
                    .clickable {
                        onHomeClick()
                    }

            ) {
                Text(text = "Home",
                    modifier=Modifier.align(Alignment.Center))
            }
        }
    }
}
@Destination
@Composable
fun HotelContent(navigator:DestinationsNavigator) {
    var post by rememberSaveable { mutableStateOf<List<PostResponse>?>(emptyList()) }
    var statelazy = rememberLazyListState()
    val viewModel:FavouritesViewModel= viewModel()
    LaunchedEffect(true){
        viewModel.fetchFavouritesHotel()
    }
    val favourites by viewModel.favourites.collectAsState()
    post=favourites
    post?.let { LazyColumn (
        state = statelazy,
        modifier = Modifier
            .fillMaxSize()){
        items(post ?: emptyList()) { postResponse ->
            HotelItem(postResponse,navigator)
        }
    }
    }
}

@Composable
fun HomeContent(navigator:DestinationsNavigator) {
    var post by rememberSaveable { mutableStateOf<List<TenantPostResponse>?>(emptyList()) }
    var statelazy = rememberLazyListState()
    val viewModel:FavouritesViewModel= viewModel()
    LaunchedEffect(true){
        viewModel.fetchFavouritesHome()
    }
    val favourites by viewModel.favouritesHome.collectAsState()
    post=favourites
    post?.let { LazyColumn (
        state = statelazy,
        modifier = Modifier
            .fillMaxSize()
            ){
        items(post ?: emptyList()) { postResponse ->
            TenantItem(postResponse,navigator)
        }
    }
    }
}