package com.route.newsappc43gsunwed.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsappc43gsunwed.R
import com.route.newsappc43gsunwed.presentation.destinations.CategoriesDestination
import kotlinx.coroutines.launch

@Composable
fun NewsDrawer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    drawerState: DrawerState
) {
    val colorScheme = MaterialTheme.colorScheme
    val scope = rememberCoroutineScope()
    ModalDrawerSheet(
        modifier = modifier.fillMaxWidth(0.7F),
        drawerState = drawerState,

        drawerContainerColor = colorScheme.background,
        drawerContentColor = colorScheme.onBackground,
        drawerShape = RectangleShape
    ) {
        Text(
            text = stringResource(R.string.news_app),
            modifier = Modifier
                .background(colorScheme.onBackground)
                .padding(horizontal = 76.dp, vertical = 64.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            color = colorScheme.background,
        )
        NewsNavigationItem(
            modifier = Modifier,
            titleResId = R.string.navigate_to_home,
            imageResId = R.drawable.ic_home
        ) {
            navController.navigate(CategoriesDestination)
            scope.launch {
                drawerState.close()
            }
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorScheme.onBackground
        )
        NewsNavigationItem(
            modifier = Modifier,
            titleResId = R.string.theme,
            imageResId = R.drawable.ic_theme
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = colorScheme.onBackground
        )
        // Assignment -> Implement Drop Down + its logic
        NewsNavigationItem(
            modifier = Modifier,
            titleResId = R.string.language,
            imageResId = R.drawable.ic_language
        )
    }
}

@Composable
fun NewsNavigationItem(
    modifier: Modifier = Modifier,
    titleResId: Int,
    imageResId: Int,
    onClick: (() -> Unit)? = null
) {
    val colorScheme = MaterialTheme.colorScheme
    NavigationDrawerItem(
        colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = colorScheme.background),
        label = {
            Text(
                stringResource(titleResId),
                modifier = Modifier,
                color = colorScheme.onBackground,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
        }, selected = true, onClick = onClick ?: {}, modifier = modifier, icon = {
            Image(
                painter = painterResource(imageResId),
                contentDescription = stringResource(titleResId),
                colorFilter = ColorFilter.tint(colorScheme.onBackground)
            )
        })
}

@Preview
@Composable
private fun NewsDrawerPreview() {
    val drawerState = rememberDrawerState(DrawerValue.Open)
    val navController = rememberNavController()
    NewsDrawer(drawerState = drawerState, navController = navController)
}
// Deadline of News App Assignment -> 9 / 4 / 2026
