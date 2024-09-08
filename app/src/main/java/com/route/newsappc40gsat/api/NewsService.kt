package com.route.newsappc40gsat.api

import com.route.newsappc40gsat.model.Category
import com.route.newsappc40gsat.model.NewsResponse
import com.route.newsappc40gsat.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") apiKey: String = "8e30e66ecc364d75967401f639e6f535",
        @Query("category") categoryId: String
    ): Call<SourcesResponse>

    @GET("everything")
    fun getNewsBySource(
        @Query("apiKey") apiKey: String = "8e30e66ecc364d75967401f639e6f535",
        @Query("sources") sourceId: String
    ): Call<NewsResponse>
}
