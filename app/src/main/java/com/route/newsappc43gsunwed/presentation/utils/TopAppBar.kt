package com.route.newsappc43gsunwed.presentation.utils

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.route.newsappc43gsunwed.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    modifier: Modifier = Modifier,
    title: String?,
    onNavigationIconClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title ?: "", fontWeight = FontWeight.W500,
                fontSize = 20.sp,

                )
        },
        actions = {
            Image(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = stringResource(
                    R.string.search_icon
                ), colorFilter = ColorFilter.tint(colorScheme.onBackground)

            )
        },
        navigationIcon = {
            Image(
                painter = painterResource(R.drawable.navigation_side_icon),
                contentDescription = stringResource(R.string.open_side_menu_icon),
                colorFilter = ColorFilter.tint(colorScheme.onBackground),
                modifier = Modifier.clickable() {
                    onNavigationIconClick()
                }
            )
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = colorScheme.background,
            titleContentColor = colorScheme.onBackground,
            actionIconContentColor = colorScheme.onBackground,
            navigationIconContentColor = colorScheme.onBackground
        )
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NewsTopAppbarPreview() {
    NewsTopAppBar(title = "News") {}
}

