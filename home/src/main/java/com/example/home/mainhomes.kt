package com.example.home
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Dehaze
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults.buttonColors
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.home.destinations.AddNewPropertyDestination
import com.example.home.destinations.ChatActivityDestination
import com.example.home.destinations.ChatScreenDestination
import com.example.home.destinations.LandlordHomeDestination
import com.example.home.destinations.TenantRecommendationDestination
import com.example.home.destinations.TouristRecommendationDestination
import com.example.home.entities.Constants
import com.example.home.tenants.module.NavHostContainer
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
@Destination
@Composable
fun Mainhome(
    navigator: DestinationsNavigator
) { var statelazy = rememberLazyListState()
    val navController = rememberNavController()
    var offset by remember { mutableStateOf(0f) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (modifier=Modifier.fillMaxWidth(0.7f)){
                Text("Renta", modifier = Modifier.padding(top=20.dp,start=16.dp))
                Button(
                    onClick = { navigator.navigate(LandlordHomeDestination) },
                    colors=buttonColors(
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
                        },
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
    ){ Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar ={
                BottomNavigationBar(navController = navController)
            },
        floatingActionButton = {
            Column (){
                FloatingActionButton(onClick = { navigator.navigate(
                    ChatActivityDestination
                ) },
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                ) {
                    Icon(Icons.Default.ChatBubble, contentDescription = "Add")
                }

                Box(modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                    .height(50.dp)
                    .width(100.dp)
                    .clickable { navigator.navigate(
                    TouristRecommendationDestination
                ) },

                ) {
                    Icon(Icons.Default.ChatBubble, contentDescription = "Add")
                 }
                Box(modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
                    .height(50.dp)
                    .width(100.dp)
                    .clickable { navigator.navigate(
                        TenantRecommendationDestination
                    ) },

                    ) {
                    Icon(Icons.Default.ChatBubble, contentDescription = "Add")
                }
            }

        }
        ) { values ->
            offset= statelazy.firstVisibleItemIndex.toFloat()
            NavHostContainer(
                navController = navController,
                padding = values,
                navigator=navigator
                )
        }
    }}
@Composable
fun AppBarShrinked() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.10f)
            .clip(
                shape = RoundedCornerShape(
                    bottomStart = 40.dp,
                    bottomEnd = 40.dp
                )
            )
            .background(
                color = MaterialTheme.colorScheme.primary
            )
    )
    {
        SearchRow(
            modifier = Modifier.padding(
                start = 10.dp,
                top = 10.dp
            )
        )
    }
}
@Composable
fun AppBarExpendable(scope: CoroutineScope, drawerstate: DrawerState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(
                shape = RoundedCornerShape(bottomStart = 40.dp)
            )
            .background(
                color = MaterialTheme.colorScheme.primary
            )
    ) {
        Icon(
            imageVector = Icons.Outlined.Dehaze,
            contentDescription = "Add icon",
            tint = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                )
                .align(Alignment.TopStart)
                .clickable {
                    scope.launch {
                        drawerstate.apply {
                            if (isClosed) open() else close()
                        }
                    }
                },
        )
        Text(
            text = "Renta",
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier
                .padding(start = 53.dp)
                .align(Alignment.TopStart)
        )
        Swipeable()
        SearchRow(
            modifier = Modifier.padding(
                start = 60.dp,
                top = 100.dp
            )
        )
    }
}
@Composable
fun SearchRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .then(modifier)
            .clip(shape = RoundedCornerShape(40.dp))
    )
    {
        SearchBox()
        Spacer(modifier = Modifier.width(5.dp))
        FilterBox()
    }
}
@Composable
fun FilterBox() {
    Box(
        modifier = Modifier
            .height(60.dp)
            .width(55.dp)
            .padding(start = 0.dp, top = 5.dp, bottom = 5.dp, end = 0.dp)
            .clip(shape = RoundedCornerShape(40.dp))
            .background(MaterialTheme.colorScheme.background)
    ) {
        Icon(
            imageVector = Icons.Outlined.Apps,
            contentDescription = "Add icon",
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 1.0f),
            modifier = Modifier
                .align(Alignment.Center)
                .size(32.dp)
        )
    }
}
@Composable
fun SearchBox() {
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(0.8f)
            .padding(start = 0.dp, top = 5.dp, bottom = 5.dp, end = 0.dp)
            .clip(shape = RoundedCornerShape(100.dp))
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Add icon",
            tint = MaterialTheme.colorScheme.outline,
            modifier = Modifier
                .padding(
                    horizontal = 10.dp
                )
                .align(Alignment.CenterStart)
        )
        Text(
            text = "Search ",
            color = colorScheme.outline,
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .padding(start = 35.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
@OptIn(ExperimentalWearMaterialApi::class)
@Composable
private fun Swipeable() {
    val width = 140.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Box(
        modifier = Modifier
            .padding(
                start = 60.dp,
                top = 50.dp
            )
            .clip(
                shape = RoundedCornerShape(40.dp)
            )
            .width(width)
            .height(40.dp)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(
                color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.5f)
            )
    ) {
        Text(
            text = "Tenant",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 5.dp)
        )
        Text(
            text = "Tourist",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 5.dp)
        )
        Box(
            Modifier
                .width(91.dp)
                .height(40.dp)
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(squareSize)
                .clip(
                    shape = RoundedCornerShape(40.dp)
                )
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .border(
                0.25.dp, color =
                MaterialTheme.colorScheme.primary
            )
            .shadow(
                40.dp,
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.inversePrimary,
                shape = RoundedCornerShape(1.dp),
            )
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selectedContentColor = MaterialTheme.colorScheme.outline,
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.label,
                        tint = MaterialTheme.colorScheme.primary

                    )
                },
                label = {
                    Text(text = navItem.label,
                        color=MaterialTheme.colorScheme.primary,
                        style=MaterialTheme.typography.bodySmall
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}
@Composable
fun NavigationDrawer(){
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text("Drawer title", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text(text = "Drawer Item") },
                    selected = false,
                    onClick = { /*TODO*/ }
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                }
            }
        }
    ){
    }
}
@Composable
fun BottomColumn(icon: Int, text: String, modifier: Modifier){
    Column(
        modifier=Modifier.then(modifier)
    ) {
        Icon(
            painter = painterResource(id =icon),
            contentDescription = "Line",
            modifier= Modifier
                .size(30.dp)
                .align(Alignment.CenterHorizontally)
            ,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier=Modifier,
            text = text,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
        )

    }
}