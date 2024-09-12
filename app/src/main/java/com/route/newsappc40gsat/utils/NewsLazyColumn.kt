package com.route.newsappc40gsat.utils



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.route.newsappc40gsat.R
import com.route.newsappc40gsat.api.ApiManager
import com.route.newsappc40gsat.model.ArticlesItem
import com.route.newsappc40gsat.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NewsLazyColumn(navHostController: NavHostController,sourceId: String, modifier: Modifier = Modifier) {
    val articlesList = remember {
        mutableStateListOf<ArticlesItem>()
    }
    LaunchedEffect(key1 = sourceId) {
        ApiManager.getNewsService().getNewsBySource(sourceId = sourceId).enqueue(
            object : Callback<NewsResponse> {
                override fun onFailure(p0: Call<NewsResponse>, p1: Throwable) {

                }
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    val articlesResponse = response.body()
                    if (articlesResponse?.articles?.isNotEmpty() == true) {
                        articlesList.clear()
                        articlesList.addAll(articlesResponse.articles)
                    }
                }
            }
        )
    }
    LazyColumn(modifier) {
        items(articlesList) { articleItem ->
            NewsCard(articleItem = articleItem){
               navHostController.navigate(articleItem)

            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsCard(articleItem: ArticlesItem, modifier: Modifier = Modifier,onArticleClick:()->Unit) {
    Card(
        onClick = onArticleClick,
        modifier.padding(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),


    ) {
        GlideImage(
            model = articleItem.urlToImage,
            contentDescription = stringResource(R.string.news_picture),
            loading = placeholder(R.drawable.logo),
            modifier = Modifier.fillMaxWidth()


        )
        Text(text = articleItem.title ?: "")
        Text(text = articleItem.publishedAt ?: "")

    }
}

@Preview
@Composable
private fun NewsCardPreview() {
    NewsCard(articleItem = ArticlesItem()){}
}

@Preview
@Composable
private fun NewsLazyColumnPreview() {
   // NewsLazyColumn("")
}
