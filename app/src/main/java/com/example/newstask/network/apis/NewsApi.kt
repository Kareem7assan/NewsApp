package com.example.newstask.network.apis

import com.example.newstask.BuildConfig
import com.example.newstask.network.model.news_model.NewsModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("articles")
    fun news(
        @Query("source") source:String,
        @Query("apiKey") apiKey:String?=BuildConfig.KEY
    ): Observable<Response<NewsModel>>

}