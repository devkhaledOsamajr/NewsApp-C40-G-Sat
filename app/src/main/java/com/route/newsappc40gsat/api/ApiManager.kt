package com.route.newsappc40gsat.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {
    private var retrofit: Retrofit? = null  // object

    init {
        initRetrofit()
    }

    fun initLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            Log.e("API", message)
        }
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    private fun initRetrofit() {
        if (retrofit == null) {
            val okhttpClient = OkHttpClient.Builder()
                .addInterceptor(initLoggingInterceptor())
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .client(okhttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }

    fun getNewsService(): NewsService {
        return retrofit!!.create(NewsService::class.java)
    }

}