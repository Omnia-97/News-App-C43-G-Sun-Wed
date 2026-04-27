package com.route.newsappc43gsunwed.utils

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.route.newsappc43gsunwed.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(
    modifier: Modifier = Modifier,
    title: String?,
    onNavigationIconClick: () -> Unit,
    onSearchQueryChange: (String) -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme
    var isSearchActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isSearchActive) {
        if (isSearchActive) focusRequester.requestFocus()
    }

    CenterAlignedTopAppBar(
        title = {
            if (isSearchActive) {
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        onSearchQueryChange(it)
                    },
                    suffix = {
                        Image(
                            painter = painterResource(R.drawable.ic_close),
                            contentDescription = "Close search",
                            colorFilter = ColorFilter.tint(colorScheme.onBackground),
                            modifier = Modifier.clickable {
                                isSearchActive = false
                                searchQuery = ""
                                onSearchQueryChange("")
                            }
                        )
                    },
                    prefix = {
                        Image(
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = stringResource(id = R.string.search),
                            colorFilter = ColorFilter.tint(colorScheme.onBackground),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    },
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.search),
                            color = colorScheme.onBackground,
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    textStyle = TextStyle(
                        color = colorScheme.onBackground,
                        fontSize = 16.sp
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        cursorColor = colorScheme.onBackground
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = colorScheme.onBackground,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .focusRequester(focusRequester),
                )
            } else {
                Text(
                    text = title ?: "", fontWeight = FontWeight.W500,
                    fontSize = 20.sp,

                    )
            }
        },
        actions = {
            if (!isSearchActive) {
                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(
                        R.string.search_icon
                    ), colorFilter = ColorFilter.tint(colorScheme.onBackground),
                    modifier = Modifier.clickable {
                        isSearchActive = true
                    }

                )
            }
        },
        navigationIcon = {
            if (!isSearchActive) {
                Image(
                    painter = painterResource(R.drawable.navigation_side_icon),
                    contentDescription = stringResource(R.string.open_side_menu_icon),
                    colorFilter = ColorFilter.tint(colorScheme.onBackground),
                    modifier = Modifier.clickable { onNavigationIconClick() }
                )
            }
        },
        modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp),
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
    NewsTopAppBar(title = "News", onSearchQueryChange = {}, onNavigationIconClick = {})
}

