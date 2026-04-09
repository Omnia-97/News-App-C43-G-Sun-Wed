package com.route.newsappc43gsunwed.presentation.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.route.newsappc43gsunwed.R
import com.route.newsappc43gsunwed.presentation.destinations.CategoriesDestination

@Composable
fun SplashScreenContent(modifier: Modifier = Modifier, navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme

    LaunchedEffect(true) {
        Handler(Looper.getMainLooper()).postDelayed(
            { navController.navigate(CategoriesDestination) },
            2_000
        )
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.news_logo),
            contentDescription = stringResource(R.string.news_app_logo),
            modifier = Modifier.fillMaxHeight(0.38F),
            contentScale = ContentScale.FillHeight
        )
        Image(
            painter = painterResource(R.drawable.signature),
            contentDescription = stringResource(R.string.development_signature),
            modifier = Modifier
                .fillMaxWidth(0.5F)
                .padding(bottom = 16.dp)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth,
            colorFilter = ColorFilter.tint(colorScheme.onBackground)
        )
    }
}

// APIs and Networking
//
