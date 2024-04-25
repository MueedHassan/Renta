package com.example.home.SplashScreen
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.home.R
import com.example.home.destinations.MainhomeDestination
import com.example.home.destinations.OnboardingDestination
import com.example.home.destinations.SignInDestination
import com.example.home.onboarding.Onboarding
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay
@Destination(start=true)
@Composable
fun SplashScreen(navigator: DestinationsNavigator){
    val colors=MaterialTheme.colorScheme.primary.value
    var targetvalue=(MaterialTheme.colorScheme.onPrimary.value).toFloat()
    val scale = remember { Animatable(initialValue =0f) }
    val color = remember { Animatable(initialValue = colors.toFloat()) }
    val vm:SplashScrrenViewModel= viewModel()
    val isFirstTimeLogin = remember { vm.isFirstTimeLogin() }

    LaunchedEffect(key1 =true,){
        scale.animateTo(
         targetValue =2f,
            animationSpec = tween(
                durationMillis =500,
                easing ={
                    OvershootInterpolator(8f).getInterpolation(it)
                }
        ))
        color.animateTo(
            targetValue=(targetvalue),
            animationSpec = tween(durationMillis=1000)
        )
        delay(3000)

        if (isFirstTimeLogin) {
            navigator.navigate(OnboardingDestination)
            vm.setFirstTimeLogin(false)
        }
        else{
            if(vm.currentUser!=null)
            {
                navigator.navigate(MainhomeDestination)
            }
            else
            { navigator.navigate(SignInDestination)
            }
        }

    }
    Box(
        modifier=Modifier.fillMaxSize()
    ){ Image(painter = painterResource(id = R.drawable.rentanew),
            contentDescription = "SplashScreen",
            modifier = Modifier
                .align(Alignment.Center)
                .scale(scale.value),
            )
    }
}