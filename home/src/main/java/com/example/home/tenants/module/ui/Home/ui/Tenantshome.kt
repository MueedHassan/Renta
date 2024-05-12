package com.example.home.tenants.module.ui.Home.ui
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults
import coil.compose.rememberAsyncImagePainter
import com.example.home.AppBarExpendable
import com.example.home.AppBarShrinked
import com.example.home.R
import com.example.home.Recommendation.ui.Tourist.TouristRecommendationVm
import com.example.home.destinations.AddNewPropertyDestination
import com.example.home.destinations.LandlordHomeDestination
import com.example.home.tenants.module.ui.Home.domain.Property
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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
            items(post!!.size) { item->
                PropertyHome( post = post!!, imageslist =imageslist ,item=item)
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
                )
                {
                    HorizontalPager(
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
                    {
                        Text(
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
fun PropertyHome(post: List<Property>, imageslist: List<Int>, item: Int) {

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
            )
            {
                HorizontalPager(
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
                {
                    Text(
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
