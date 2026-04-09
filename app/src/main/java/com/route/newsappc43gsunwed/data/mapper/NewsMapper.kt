package com.route.newsappc43gsunwed.data.mapper

import com.route.newsappc43gsunwed.data.model.ArticlesItemDM
import com.route.newsappc43gsunwed.data.model.SourcesItemDM
import com.route.newsappc43gsunwed.domain.model.ArticlesItem
import com.route.newsappc43gsunwed.domain.model.SourcesItem

fun SourcesItemDM.toSourceItem(): SourcesItem {
    return SourcesItem(name = name, id = id, category = category)
}

fun SourcesItem.toSourceItemDM(): SourcesItemDM {
    return SourcesItemDM(name = name, id = id, category = category)
}

fun ArticlesItemDM.toArticleItem(): ArticlesItem {
    return ArticlesItem(
        publishedAt = publishedAt,
        author = author,
        urlToImage = urlToImage,
        description = description,
        title = title,
        url = url,
        content = content,
        sourceId = sourceId
    )
}

fun ArticlesItem.toArticlesItemDM(): ArticlesItemDM {
    return ArticlesItemDM(
        publishedAt = publishedAt,
        author = author,
        urlToImage = urlToImage,
        description = description,
        title = title,
        url = url,
        content = content,
        sourceId = sourceId
    )
}
