package com.route.newsappc40gsat.fragments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.route.newsappc40gsat.R
import com.route.newsappc40gsat.model.Category

const val CATEGORIES_SCREEN = "categories"

@Composable
fun CategoriesScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        Text(
            text = "Pick your category \nof interest", modifier = Modifier.padding(12.dp)
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            itemsIndexed(Category.getCategoriesList()) { index, item ->
                CategoryCard(category = item, index = index, modifier = Modifier.padding(4.dp)) {
                    navController.navigate(item)
                }
            }
        }
    }
}

@Composable
fun CategoryCard(
    category: Category,
    index: Int,
    modifier: Modifier = Modifier,
    onCategoryClick: () -> Unit
) {
    val navController = rememberNavController()
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier.background(
            colorResource(id = category.backgroundColor),
            RoundedCornerShape(
                topStart = 24.dp,
                topEnd = 24.dp,
                bottomStart = if (index % 2 == 0) 24.dp else 0.dp,
                bottomEnd = if (index % 2 == 1) 24.dp else 0.dp
            )
        ),
        onClick = {
            onCategoryClick()

        }
    ) {
        Image(
            painter = painterResource(id = category.drawableResId),
            contentDescription = stringResource(
                R.string.category_image
            ),
            modifier = Modifier
                .height(120.dp)
                .align(Alignment.CenterHorizontally), contentScale = ContentScale.Crop
        )
        Text(
            text = stringResource(id = category.titleResID), color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun CategoriesContentPreview() {
    CategoriesScreen(rememberNavController())
}