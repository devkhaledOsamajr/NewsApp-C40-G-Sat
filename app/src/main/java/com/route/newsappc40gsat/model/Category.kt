package com.route.newsappc40gsat.model

import com.route.newsappc40gsat.R
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val apiID: String, // sports
    val drawableResId: Int, // R.drawable.sports
    val titleResID: Int, // R.string.sports
    val backgroundColor: Int
) : java.io.Serializable {
    companion object {
        fun getCategoriesList(): List<Category> = listOf(
            Category(
                "sports", R.drawable.ball,
                R.string.sports, R.color.red
            ),
            Category(
                "technology", R.drawable.politics,
                R.string.technology, R.color.blue
            ),
            Category(
                "health", R.drawable.health,
                R.string.health, R.color.pink
            ),
            Category(
                "business", R.drawable.bussines,
                R.string.business, R.color.brown_orange
            ),
            Category(
                "general", R.drawable.environment,
                R.string.general, R.color.baby_blue
            ),
            Category(
                "science", R.drawable.science,
                R.string.science, R.color.yellow
            ),
        )
    }
}