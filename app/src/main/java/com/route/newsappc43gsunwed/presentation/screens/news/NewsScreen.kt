package com.route.newsappc43gsunwed.presentation.screens.news

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.route.newsappc43gsunwed.R
import com.route.newsappc43gsunwed.data.model.ArticlesItemDM
import com.route.newsappc43gsunwed.data.model.SourcesItemDM
import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.SourcesItem
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    categoryApiId: String,
    navController: NavHostController
) {
    val viewModel: NewsViewModel = hiltViewModel()
    val colorScheme = MaterialTheme.colorScheme
    val sourcesList = viewModel.sourcesLiveData.observeAsState()
    val articlesList = viewModel.articlesLiveData.observeAsState()
    val isLoading = viewModel.isLoading.observeAsState()
    val errorState = viewModel.errorLiveData.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.getSourcesByCategory(categoryApiId)
    }
    if (isLoading.value == true) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = colorScheme.onBackground)
        }
    }
    if (errorState.value?.isNotEmpty() == true) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = errorState.value ?: "", color = colorScheme.onBackground)
        }
    }

    Column(modifier = modifier.fillMaxSize()) {
        NewsSourcesLazyRow(
            modifier = Modifier,
            sourcesList = sourcesList.value?.toPersistentList() ?: persistentListOf()
        ) { index ->
            viewModel.getArticlesBySourceId(sourcesList.value?.get(index)?.id ?: "")
        }
        Spacer(modifier = Modifier.padding(2.dp))
        NewsListLazyColumn(
            modifier = Modifier,
            articlesList = articlesList.value?.toPersistentList() ?: persistentListOf()
        )
    }
}

@Composable
fun NewsListLazyColumn(modifier: Modifier, articlesList: PersistentList<ArticlesItem>) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(articlesList) { articleItem ->
            NewsCard(articlesItemDM = articleItem)
        }
    }
}


@Composable
fun NewsCard(modifier: Modifier = Modifier, articlesItemDM: ArticlesItem) {
    val colorScheme = MaterialTheme.colorScheme
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = colorScheme.onBackground
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.border(1.dp, colorScheme.onBackground, RoundedCornerShape(16.dp))
    ) {
        AsyncImage(
            model = articlesItemDM.urlToImage ?: "",
            contentDescription = articlesItemDM.description ?: "",
            placeholder = painterResource(R.drawable.news_logo),
            error = painterResource(R.drawable.business),
            modifier = Modifier
                .padding(8.dp)
                .height(220.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),


            )
        Text(
            text = articlesItemDM.title ?: "",
            maxLines = 2,
            color = colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 8.dp),

            )
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = articlesItemDM.author ?: "",
                maxLines = 1,
                color = colorScheme.onBackground,
                modifier = Modifier,

                )
            Text(
                text = articlesItemDM.publishedAt ?: "",
                maxLines = 1,
                color = colorScheme.onBackground,
                modifier = Modifier,

                )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsCardPreview() {
    NewsCard(
        articlesItemDM = ArticlesItem(
            publishedAt = "18-3-2026",
            author = "ABC News",
            description = "null",
            title = "Text Text Text"

        )
    )
}

@Composable
fun NewsSourcesLazyRow(
    modifier: Modifier = Modifier,
    sourcesList: PersistentList<SourcesItem>,
    onTabClick: (index: Int) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val selectedTextStyle = TextStyle(
        fontSize = 16.sp, fontWeight = FontWeight.W700, color = colorScheme.onBackground
    )
    val nonSelectedTextStyle = TextStyle(
        fontSize = 14.sp, fontWeight = FontWeight.W500, color = colorScheme.onBackground
    )
    if (sourcesList.isNotEmpty()) {

        LazyRow(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(sourcesList) { index, source ->
                Tab(
                    selectedIndex == index,
                    onClick = {
                        onTabClick(index)
                        selectedIndex = index
                    },
                    modifier = Modifier,
                    selectedContentColor = colorScheme.onBackground,
                    unselectedContentColor = colorScheme.onBackground,

                    ) {
                    Text(
                        text = source.name ?: "",
                        style = if (selectedIndex == index) selectedTextStyle else nonSelectedTextStyle,
                        textDecoration = if (selectedIndex == index) TextDecoration.Underline else TextDecoration.None
                    )
                    if (selectedIndex == index) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = colorScheme.onBackground,
                            modifier = Modifier.fillMaxWidth()

                        )
                    }
                }
            }

        }
        LaunchedEffect(Unit) {
            onTabClick(0)
        }
    }
}
/**
 *  1- when opening News Screen It doesn't load news list initially
 *  2- Interceptor
 *  3- Image loading from URL (https://www.google.com/image.png)
 *  4- Side Menu (Drawer)
 *
 */
