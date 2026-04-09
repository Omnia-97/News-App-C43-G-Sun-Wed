package com.route.newsappc43gsunwed.presentation.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.route.newsappc43gsunwed.R
import com.route.newsappc43gsunwed.presentation.destinations.NewsDestination
import com.route.newsappc43gsunwed.data.model.CategoryDM

@Composable
fun CategoriesScreen(modifier: Modifier = Modifier, navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme
    // Column with Vertical Scroll  (List View )
    // LazyColumn                   (RecyclerView )
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.good_morning_here_is_some_news_for_you),
                color = colorScheme.onBackground,
                fontWeight = FontWeight.W500,
                fontSize = 24.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        //(PersistentList)
        //  Int
        // Count -> %2 == 0 -> Even
        //          %2 == 1 -> Odd

        itemsIndexed(CategoryDM.getCategoriesDM()) { index, item ->
            Log.e("TAG", "CategoriesScreen: $index")
            CategoriesCard(modifier = Modifier, index, item) { categoryDM ->
                navController.navigate(NewsDestination(categoryDM.categoryApiId ?: ""))
            }
        }

    }
}

@Preview
@Composable
private fun CategoryCardEvenPreview() {
    CategoriesCard(index = 0, item = CategoryDM.getCategoriesDM().get(0)) { }
}

@Preview
@Composable
private fun CategoryCardOddPreview() {
    CategoriesCard(index = 1, item = CategoryDM.getCategoriesDM().get(0)) { }
}

@Composable
fun CategoriesCard(
    modifier: Modifier = Modifier,
    index: Int,
    item: CategoryDM,
    onCardClick: (item: CategoryDM) -> Unit
) {
    if (index % 2 == 0) {
        EvenCategoryCard(modifier = modifier, item) {
            onCardClick(item)
        }
    } else {
        OddCategoryCard(modifier = modifier, item) {
            onCardClick(item)
        }
    }
}

@Composable
fun OddCategoryCard(
    modifier: Modifier = Modifier,
    item: CategoryDM,
    onCardClick: (item: CategoryDM) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        onClick = {
            onCardClick(item)
        },
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.onBackground,
            contentColor = colorScheme.background,
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .height(200.dp)
                .padding(start = 16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // title
                Text(
                    text = stringResource(item.title ?: R.string.business),
                    color = colorScheme.background,
                    fontWeight = FontWeight.W400,
                    fontSize = 20.sp,
                )
                Box(
                    modifier = Modifier.background(
                        color = colorScheme.tertiary,
                        shape = CircleShape
                    )
                ) {
                    Text(
                        stringResource(R.string.view_all), modifier = Modifier
                            .padding(start = 64.dp, top = 10.dp, bottom = 10.dp, end = 16.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_arrow_reverse),
                        contentDescription = stringResource(R.string.news_category_filter),
                        colorFilter = ColorFilter.tint(colorScheme.onBackground),
                        modifier = Modifier
                            .background(colorScheme.background, CircleShape)
                            .align(Alignment.CenterStart)
                    )
                }
            }

            // Category Image
            Image(
                painter = painterResource(item.image ?: R.drawable.business),
                contentDescription = stringResource(R.string.news_category_filter),
                modifier = Modifier
                    .fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

@Composable
fun EvenCategoryCard(
    modifier: Modifier = Modifier,
    item: CategoryDM,
    onCardClick: (item: CategoryDM) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        onClick = {
            onCardClick(item)
        },
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.onBackground,
            contentColor = colorScheme.background,
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row(
            modifier = Modifier
                .height(200.dp)
                .padding(end = 16.dp)
        ) {
            // Category Image
            Image(
                painter = painterResource(item.image ?: R.drawable.business),
                contentDescription = stringResource(R.string.news_category_filter),
                modifier = Modifier
                    .fillMaxHeight(),
                contentScale = ContentScale.FillHeight
            )
            Spacer(modifier = Modifier.weight(1F))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                // title
                Text(
                    text = stringResource(item.title ?: R.string.business),
                    color = colorScheme.background,
                    fontWeight = FontWeight.W400,
                    fontSize = 20.sp,
                )
                Box(
                    modifier = Modifier.background(
                        color = colorScheme.tertiary,
                        shape = CircleShape
                    )
                ) {
                    Text(
                        stringResource(R.string.view_all), modifier = Modifier
                            .padding(start = 16.dp, top = 10.dp, bottom = 10.dp, end = 64.dp)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_arrow),
                        contentDescription = stringResource(R.string.news_category_filter),
                        colorFilter = ColorFilter.tint(colorScheme.onBackground),
                        modifier = Modifier
                            .background(colorScheme.background, CircleShape)
                            .align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}
