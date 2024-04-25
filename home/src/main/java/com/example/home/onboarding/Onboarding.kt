package com.example.home.onboarding

import android.content.Context
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.home.R
import com.example.home.destinations.SignUpPageDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun Onboarding(navigator: DestinationsNavigator){
    val pagerState = rememberPagerState(pageCount = {
        3
    })
    val scope = rememberCoroutineScope()
    val images = listOf(
        R.drawable.imain,
        R.drawable.i5,
        R.drawable.i2
    )
    val textHeading = listOf(
        "Welcome To Renta",
        "Discover Endless Rental Possibilities",
        "Rent Smarter, Live Better"
    )
    val textSubHeading = listOf(
        "Browse Thousands of Listings",
        "Tailored Recommendations Just for You",
        "Sign In To Continue"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state=pagerState,
            pageSize = PageSize.Fill
        ) { index ->

            Box(modifier=Modifier.fillMaxSize())
            {
                Image(
                    painter = painterResource(id = images[index]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                OnBoardingGradient(modifier = Modifier.align(Alignment.BottomCenter))
                OnBoardingCardFooter(modifier = Modifier.align(Alignment.BottomCenter),
                    context= LocalContext.current,
                    heading=textHeading[index],
                    subheading = textSubHeading[index],
                    scope=scope,
                    pagerState=pagerState,
                    navigator=navigator
                )
            }




        }

    }

}
@Composable
fun OnBoardingGradient(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .background(
                Brush.verticalGradient(
                    listOf(
                        Color(0x00FFFFFF),
                        Color(0xB3000000),
                        Color(0xFF000000),
                    )
                )
            )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingCardFooter(
    modifier: Modifier,
    context: Context,
    heading: String,
    subheading: String,
    scope: CoroutineScope,
    pagerState: PagerState,
    navigator: DestinationsNavigator

) {

    Box(
        modifier = Modifier
            .then(modifier)
            .padding(16.dp)
            .fillMaxWidth()
            .height(154.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),

            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(

                modifier = Modifier.fillMaxWidth(),
                fontSize = 22.sp,
                lineHeight = 28.sp,
                textAlign = TextAlign.Center,
                text =heading ,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight(500)),
                letterSpacing = 0.1.sp,
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                textAlign = TextAlign.Center,
                text = subheading,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight(600)),
                letterSpacing = 0.5.sp,
            )
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(30.dp))
                    .fillMaxWidth()
                    .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1,
                                    0f,
                                    tween(2000)
                                )
                                delay(3000)
                                if (pagerState.currentPage == 2) {
                                    navigator.navigate(SignUpPageDestination)
                                }
                            }
                    }
                    .padding()
                    .height(48.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    )
            ) {
                if(pagerState.currentPage<2)
                {
                    Text(
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        textAlign = TextAlign.Center,
                        text ="Next",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500)),
                        letterSpacing = 0.1.sp,
                    )

                }
                else{
                    Text(
                        modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                        fontSize = 22.sp,
                        lineHeight = 28.sp,
                        textAlign = TextAlign.Center,
                        text ="Sign Up",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight(500)),
                        letterSpacing = 0.1.sp,
                    )

                }
            }
            Row(
            )
            {
                Text(
                    text = context.getString(R.string.terms_conditions),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight(500)),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,

                    )

                Text(

                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    textAlign = TextAlign.Center,

                    text = context.getString(R.string.privacy_policy),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight(500)),

                    )

            }


        }
    }}

