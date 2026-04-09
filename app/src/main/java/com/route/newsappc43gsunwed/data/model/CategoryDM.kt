package com.route.newsappc43gsunwed.data.model

import androidx.compose.runtime.Immutable
import com.route.newsappc43gsunwed.R
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class CategoryDM(
    val title: Int? = null,
    val categoryApiId: String? = null,
    val image: Int? = null
) {
    companion object {
        fun getCategoriesDM(): PersistentList<CategoryDM> {
            return persistentListOf(
                CategoryDM(
                    title = R.string.general,
                    categoryApiId = "general",
                    image = R.drawable.earth
                ),
                CategoryDM(
                    title = R.string.business,
                    categoryApiId = "business",
                    image = R.drawable.business
                ),
                CategoryDM(
                    title = R.string.sports,
                    categoryApiId = "sports",
                    image = R.drawable.sports
                ),
                CategoryDM(
                    title = R.string.technology,
                    categoryApiId = "technology",
                    image = R.drawable.technology
                ),
                CategoryDM(
                    title = R.string.science,
                    categoryApiId = "science",
                    image = R.drawable.science
                ),
                CategoryDM(
                    title = R.string.health,
                    categoryApiId = "health",
                    image = R.drawable.health
                ),
                CategoryDM(
                    title = R.string.entertainment,
                    categoryApiId = "entertainment",
                    image = R.drawable.entertainment
                ),

                )
        }
    }
}
