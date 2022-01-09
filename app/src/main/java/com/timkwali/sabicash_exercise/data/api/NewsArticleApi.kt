package com.timkwali.sabicash_exercise.data.api

import com.timkwali.sabicash_exercise.common.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsArticleApi {

    @GET("v2/top-headlines")
    suspend fun getNewsArticles(
        @Query("country") Country: String = "us",
        @Query("page") pageNumber: Int = 1,
        apiKey: String = Constants.API_KEY
    ) {

    }
}