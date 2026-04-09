package com.route.newsappc43gsunwed.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.route.newsappc43gsunwed.R
import com.route.newsappc43gsunwed.presentation.destinations.CategoriesDestination
import com.route.newsappc43gsunwed.presentation.destinations.NewsDestination
import com.route.newsappc43gsunwed.presentation.destinations.SplashDestination
import com.route.newsappc43gsunwed.presentation.screens.CategoriesScreen
import com.route.newsappc43gsunwed.presentation.screens.SplashScreenContent
import com.route.newsappc43gsunwed.presentation.screens.news.NewsScreen
import com.route.newsappc43gsunwed.presentation.ui.theme.NewsAppC43GSunWedTheme
import com.route.newsappc43gsunwed.presentation.utils.NewsDrawer
import com.route.newsappc43gsunwed.presentation.utils.NewsTopAppBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppC43GSunWedTheme {
                NewsApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun NewsApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentBackStackEntryState by navController.currentBackStackEntryAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            NewsDrawer(
                modifier = Modifier,
                drawerState = drawerState,
                navController = navController
            )
        },
        drawerState = drawerState
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                Log.e(
                    "NAV",
                    "NewsApp: Navigation Route ->  ${currentBackStackEntryState?.destination?.route}"
                )
                if (currentBackStackEntryState?.destination?.route != SplashDestination::class.qualifiedName)
                    NewsTopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        stringResource(R.string.home)
                    ) {
                        scope.launch {
                            drawerState.open() // Kotlin Coroutines
                        }
                    }
            }) {
            NavHost(
                navController = navController,
                startDestination = SplashDestination,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()

            ) {
                //composable<HomeDestination>
                //composable<LoginDestination>
                //composable<CategoriesDestination>
                composable<SplashDestination> {
                    SplashScreenContent(navController = navController)
                }
                composable<CategoriesDestination> {
                    CategoriesScreen(navController = navController)
                }
                composable<NewsDestination> { backStackEntry ->
                    val newsDestination = backStackEntry.toRoute<NewsDestination>()
                    NewsScreen(
                        categoryApiId = newsDestination.categoryApiId,
                        navController = navController,
                    )
                }
            }
        }
    }
}

/**
 *   1- Navigation Component
 *   2- News App UI
 *
 *
 *
 *   APIs & Networking
 *
 */
