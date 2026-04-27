package com.route.newsappc43gsunwed.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.route.newsappc43gsunwed.R
import com.route.newsappc43gsunwed.api.ApiManager
import com.route.newsappc43gsunwed.model.ArticlesItem
import com.route.newsappc43gsunwed.model.ArticlesResponse
import com.route.newsappc43gsunwed.model.SourcesItem
import com.route.newsappc43gsunwed.model.SourcesResponse
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    categoryApiId: String,
    navController: NavHostController
) {
    val colorScheme = MaterialTheme.colorScheme
    val context = LocalContext.current
    val sourcesList = remember { mutableStateListOf<SourcesItem>() }
    val articlesList = remember { mutableStateListOf<ArticlesItem>() }
    LaunchedEffect(Unit) {
        getSourcesByCategory(categoryApiId) {
            sourcesList.addAll(it)
        }
    }
    Column(modifier = modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        NewsSourcesLazyRow(
            modifier = Modifier,
            sourcesList = sourcesList.toPersistentList()
        ) { index ->
            getArticlesBySourceId(sourcesList[index].id ?: "") {
                Log.e("TAG321", "NewsScreen: Source id = ${sourcesList[index].id}")
                Log.e("TAG321", "NewsScreen: News List => $it")
                articlesList.clear()
                articlesList.addAll(it)
            }
        }
        Spacer(modifier = Modifier.padding(2.dp))
        NewsListLazyColumn(modifier = Modifier, articlesList = articlesList.toPersistentList())
    }
}

@Composable
fun NewsListLazyColumn(modifier: Modifier, articlesList: PersistentList<ArticlesItem>) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(articlesList) { articleItem ->
            NewsCard(articlesItem = articleItem)
        }
    }
}

fun getArticlesBySourceId(sourceId: String, onArticlesResponse: (List<ArticlesItem>) -> Unit) {
    ApiManager.getNewsService().getNewsBySourceId(sourceId).enqueue(
        object : Callback<ArticlesResponse> {
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                val articles = response.body()?.articles ?: listOf()
                onArticlesResponse(articles)
            }

            override fun onFailure(
                call: Call<ArticlesResponse?>?,
                error: Throwable?
            ) {
                Log.e("TAG", "onFailure: ${error?.message}")
            }

        }
    )
}

fun getSourcesByCategory(categoryApiId: String, onSourcesResponse: (List<SourcesItem>) -> Unit) {

    ApiManager.getNewsService().getSources(categoryApiId = categoryApiId)
        .enqueue(object : Callback<SourcesResponse> {
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {
                val sources = response.body()?.sources ?: listOf()
                onSourcesResponse(sources)
                Log.e("TAG", "onResponse: ${response.body()}")
            }

            override fun onFailure(p0: Call<SourcesResponse?>?, p1: Throwable?) {
                Log.e("Error", p1?.message ?: "")
            }
        })
    //.execute()  X // Execute ->  Main Thread or UI Thread
}

@Composable
fun NewsCard(modifier: Modifier = Modifier, articlesItem: ArticlesItem) {
    val colorScheme = MaterialTheme.colorScheme
    var showBottomSheet by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = colorScheme.onBackground
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.border(1.dp, colorScheme.onBackground, RoundedCornerShape(16.dp))
            .clickable { showBottomSheet = true }
    ) {
        AsyncImage(
            model = articlesItem.urlToImage ?: "",
            contentDescription = articlesItem.description ?: "",
            placeholder = painterResource(R.drawable.news_logo),
            error = painterResource(R.drawable.business),
            modifier = Modifier
                .padding(8.dp)
                .height(220.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),


            )
        Text(
            text = articlesItem.title ?: "",
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
                text = articlesItem.author ?: "",
                maxLines = 1,
                color = colorScheme.onBackground,
                modifier = Modifier,

                )
            Text(
                text = articlesItem.publishedAt ?: "",
                maxLines = 1,
                color = colorScheme.onBackground,
                modifier = Modifier,

                )
        }
    }
    if (showBottomSheet) {
        ArticleDetailsBottomSheet(
            articlesItem = articlesItem,
            onDismiss = { showBottomSheet = false }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsCardPreview() {
    NewsCard(
        articlesItem = ArticlesItem(
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
