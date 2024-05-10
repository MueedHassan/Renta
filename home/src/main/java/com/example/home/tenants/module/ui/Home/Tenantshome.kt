package com.example.home.tenants.module.ui.Home
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import com.example.home.AppBarExpendable
import com.example.home.AppBarShrinked
import com.example.home.R
import com.example.home.Recommendation.ui.Tourist.TouristRecommendationVm
import com.example.home.destinations.AddNewPropertyDestination
import com.example.home.destinations.LandlordHomeDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
@Composable
fun TenantHome(navigator: DestinationsNavigator) {
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
            if (offset.toInt() == 0) {
                AppBarExpendable(drawerstate = drawerState, scope = scope)
            } else {
                AppBarShrinked()
            }
        },

        ) { values ->
        offset = lazyListState.firstVisibleItemIndex.toFloat()
        val imageslist = listOf(
            R.drawable.home1,
            R.drawable.home3,
            R.drawable.home4,
            R.drawable.home6,
        )
        val imagetextlist = listOf(
            "Villa",
            "Bungalow",
            "Studio Apartment",
            "Shared Room",
            "Home",
            "Flat"
        )
        val imagepricelist = listOf(
            "$85", "$65",
            "$15", "$895",
            "$850", "$650"
        )
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(values)

        ) {
            items(imageslist.size) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(shape = RoundedCornerShape(15.dp))
                        .fillParentMaxWidth(0.9f)
                ) {
                    Image(
                        painter = painterResource(id = imageslist[it]), contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                            .fillParentMaxWidth()
                            .height(200.dp)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = imagetextlist[it],
                        style = MaterialTheme.typography.headlineMedium.copy(),
                        color = Color.Black,
                        modifier = Modifier
                            .padding(
                                start = 40.dp,
                                bottom = 35.dp
                            )
                            .align(Alignment.BottomStart)
                    )
                    Text(
                        text = imagepricelist[it],
                        style = MaterialTheme.typography.headlineSmall.copy(),
                        color = Color.Black,
                        modifier = Modifier
                            .padding(
                                start = 40.dp,
                                bottom = 10.dp
                            )
                            .align(Alignment.BottomStart)
                    )
                }

            }
        }
    }
    }
}