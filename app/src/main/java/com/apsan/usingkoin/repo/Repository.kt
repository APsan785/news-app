package com.apsan.usingkoin.repo

import android.util.Log
import com.apsan.usingkoin.api.NewsApi
import com.apsan.usingkoin.models.Articles

class Repository(val newsApi: NewsApi) {

    val TAG = "tag"

    suspend fun getTopNews() : ArrayList<Articles>?{
        val response = newsApi.getTopNews()
        if (response.isSuccessful){
            return response.body()?.articles
        }else{
            Log.e(TAG, "getTopNews: ${response.errorBody()}")
        }
        return null
    }

    suspend fun getTopNewsSearch(query : String) : ArrayList<Articles>?{
        val response = newsApi.getTopNewsSearch(query)
        if (response.isSuccessful){
            return response.body()?.articles
        }else{
            Log.e(TAG, "getTopNews: ${response.errorBody().toString()}")
        }
        return null
    }
}