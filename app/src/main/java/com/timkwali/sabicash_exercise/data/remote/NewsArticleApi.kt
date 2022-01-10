package com.timkwali.sabicash_exercise.data.remote

import com.timkwali.sabicash_exercise.common.Constants
import com.timkwali.sabicash_exercise.domain.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsArticleApi {

    @GET("v2/top-headlines")
    suspend fun getNewsArticles(
        @Query("country") country: String = "us",
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") perPage: Int,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): NewsResponse
}