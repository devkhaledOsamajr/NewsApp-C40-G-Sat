package com.route.newsappc40gsat.fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ScrollableTabRow
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
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.route.newsappc40gsat.Colors
import com.route.newsappc40gsat.R
import com.route.newsappc40gsat.api.ApiManager
import com.route.newsappc40gsat.model.Category
import com.route.newsappc40gsat.model.SourcesItem
import com.route.newsappc40gsat.model.SourcesResponse
import com.route.newsappc40gsat.utils.NewsLazyColumn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun NewsScreen(navHostController: NavHostController,category: Category, modifier: Modifier = Modifier) {
    val sourcesListState = remember {
        mutableStateListOf<SourcesItem>()
    }

    NewsContent(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds
            ),
        sourcesList = sourcesListState,
        navHostController = navHostController
    )

    LaunchedEffect(Unit) {
        ApiManager.getNewsService().getNewsSources(categoryId = category.apiID)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(p0: Call<SourcesResponse>, p1: Throwable) {

                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    val sourcesResponse = response.body()
                    if (sourcesResponse?.sources?.isNotEmpty() == true)
                        sourcesListState.addAll(sourcesResponse.sources)
                }
            }) // Background Thread (Database Calls and API Calls)
//                            .execute() // Main Thread (UI Thread - Navigation - Button Clicks - Animation)
    }
}


@Composable
fun NewsContent(navHostController: NavHostController,sourcesList: List<SourcesItem>, modifier: Modifier = Modifier) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    var selectedSourceId by remember {
        mutableStateOf("")
    }
    val selectedModifier = Modifier
        .padding(2.dp)
        .background(color = Colors.green, shape = CircleShape)
        .padding(horizontal = 12.dp, vertical = 4.dp)
    val nonSelectedModifier = Modifier
        .padding(2.dp)
        .border(2.dp, color = Colors.green, CircleShape)
        .padding(horizontal = 12.dp, vertical = 4.dp)
    if (sourcesList.isNotEmpty())
        Column(modifier = modifier) {
            LaunchedEffect(key1 = Unit) {
                selectedSourceId = sourcesList[0].id ?: ""
            }
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                divider = {},
                indicator = {},
                edgePadding = 0.dp
            ) {
                sourcesList.forEachIndexed { index, sourceItem ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedSourceId = sourceItem.id ?: ""
                            selectedTabIndex = index
                        },
                        modifier = if (selectedTabIndex == index) selectedModifier else nonSelectedModifier

                    ) {
                        Text(
                            text = "${sourceItem.name}",
                            color = if (selectedTabIndex == index) Color.White else Colors.green
                        )
                    }
                }
            }
            if (selectedSourceId.isNotEmpty())
                NewsLazyColumn(navHostController = navHostController,selectedSourceId)
        }
}

@Preview
@Composable
private fun NewsContentPreview() {
  //  NewsContent(listOf())
}
/**
 * News App  (APIs concept - RESTful APIS concept-> Retrofit )
 * MVVM -> Model View View Model -> ViewModel
 * Chat Application (Firebase)
 *
 * News App -> Dependency Injection using Dagger Hilt & Repository Pattern
 * News App -> Coroutines
 * MVI UI Architecture Pattern
 * Clean Architecture (Multi Module App)
 * Unit Testing
 * UI Testing
 * Flows and StateFlow
 *
 *
 *
 *
 *
 */
