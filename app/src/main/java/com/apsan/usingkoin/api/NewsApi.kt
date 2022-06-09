package com.apsan.usingkoin.api

import com.apsan.usingkoin.di.apiModule
import com.apsan.usingkoin.models.NewsAPIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApi {

    companion object {
        const val APIKEY = "b1fd78d09ec443599097ff01013b3541"
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines?country=in")
    @Headers("X-Api-Key: $APIKEY")
    suspend fun getTopNews(): Response<NewsAPIResponse>

    @GET("top-headlines")
    @Headers("X-Api-Key: $APIKEY")
    suspend fun getTopNewsSearch(@Query("q") q: String): Response<NewsAPIResponse>

}