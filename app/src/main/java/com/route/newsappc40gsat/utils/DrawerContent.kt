package com.route.newsappc40gsat.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.route.newsappc40gsat.Colors
import com.route.newsappc40gsat.R
import com.route.newsappc40gsat.api.NewsService
import com.route.newsappc40gsat.fragments.CATEGORIES_SCREEN

@Composable
fun NewsDrawerContent(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    onDrawerItemClick: () -> Unit
) {
    ModalDrawerSheet {
        Text(
            text = stringResource(R.string.news_app),
            modifier = Modifier
                .background(Colors.green)
                .height(80.dp)
                .fillMaxWidth(0.7F)
                .padding(vertical = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            color = Color.White
        )
        NewsDrawerItem(
            text = stringResource(R.string.categories),
            imageResId = R.drawable.ic_categories
        ) {
            navHostController.popBackStack()
            if (navHostController.currentDestination?.route != CATEGORIES_SCREEN) {
                navHostController.navigate(CATEGORIES_SCREEN)
            }
            onDrawerItemClick()
        }
        NewsDrawerItem(
            text = stringResource(R.string.settings),
            imageResId = R.drawable.ic_settings
        ) {
            //
            onDrawerItemClick()
        }
    }
}

@Composable
fun NewsDrawerItem(
    modifier: Modifier = Modifier,
    text: String,
    imageResId: Int,
    onNavigationDrawerClick: () -> Unit
) {
    Row(modifier.clickable { onNavigationDrawerClick() }) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = stringResource(R.string.item_drawer),
            modifier = Modifier.padding(8.dp)
        )
        Text(text = text, fontSize = 20.sp, modifier = Modifier.padding(8.dp))

    }
}