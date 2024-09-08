package com.route.newsappc40gsat

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.route.newsappc40gsat.ui.theme.NewsAppC40GSatTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsAppC40GSatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SplashContent(modifier = Modifier.padding(innerPadding)) {
                        finish()
                    }
                }
            }
        }
    }
}

@Composable
fun SplashContent(modifier: Modifier = Modifier, onFinish: () -> Unit) {
    Column(
        modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds
            ), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.logo_of_the_application),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight(0.3F)
        )
        Spacer(modifier = Modifier.weight(1F))
        Image(
            painter = painterResource(id = R.drawable.singature),
            contentDescription = stringResource(
                R.string.application_development_signature
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(0.5F)
        )
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        val intent = Intent(context, NewsActivity::class.java)
        context.startActivity(intent)
        onFinish()
    }
}

@Preview
@Composable
private fun SplashContentPreview() {
    SplashContent {

    }
}

