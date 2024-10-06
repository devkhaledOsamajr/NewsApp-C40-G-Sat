package com.route.newsappc40gsat.fragments


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.route.newsappc40gsat.R
import com.route.newsappc40gsat.model.ArticlesItem

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailScreen(article: ArticlesItem, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        GlideImage(
            model = article.urlToImage,
            contentDescription = stringResource(R.string.news_picture),
            loading = placeholder(R.drawable.logo),
            contentScale = ContentScale.FillWidth,
        )
        Text(
            text = "${article.title}",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()


        )
        Text(
            text = "${article.content}",

            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)

        )
    }


}