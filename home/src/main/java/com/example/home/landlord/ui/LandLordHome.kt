package com.example.home.landlord.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.outlined.Dehaze
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults.buttonColors
import com.example.home.destinations.AddNewPropertyDestination
import com.example.home.destinations.ChatActivityDestination
import com.example.home.destinations.MainhomeDestination
import com.example.home.landlord.ui.components.LandLordConstants
import com.example.home.landlord.ui.components.LandLordNavHostContainer
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Destination
@Composable
fun LandlordHome(
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
                    onClick = { navigator.navigate(MainhomeDestination) },
                    colors=buttonColors(
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor =MaterialTheme.colorScheme.inverseOnSurface
                    ),
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .clip(shape = RoundedCornerShape(1.dp))
                        .height(40.dp)
                        .fillMaxWidth(0.80f)
                        .align(Alignment.CenterHorizontally),
                ){ Text(
                        text =" Switch To Tenants/Host",
                        style=MaterialTheme.typography.bodyLarge)
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
        topBar ={  Box(
            modifier = Modifier
                .fillMaxWidth()
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
                            drawerState.apply {
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
        }

        },
        bottomBar ={
            LandLordBottomNavigationBar(navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigator.navigate(
                ChatActivityDestination
            ) },
                modifier = Modifier.height(50.dp).width(50.dp)
            ) {
                Icon(Icons.Default.ChatBubble, contentDescription = "Add")
            }
        }
    ) { values ->
        offset= statelazy.firstVisibleItemIndex.toFloat()
        LandLordNavHostContainer(
            navController = navController,
            padding = values,
            navigator=navigator
        )

    }
    }}
@Composable
fun LandLordBottomNavigationBar(navController: NavHostController) {
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
        LandLordConstants.LandLordBottomNavItems.forEach {navItem->
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
    }
}