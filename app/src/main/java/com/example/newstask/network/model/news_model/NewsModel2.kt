package com.example.newstask.network.model.news_model

import com.example.newstask.network.model.BaseResponse
import com.google.gson.annotations.SerializedName

data class NewsModel2(
    @SerializedName("articles")
    val articles: List<ArticleX?>?,
    @SerializedName("sortBy")
    val sortBy: String?,
    @SerializedName("source")
    val source: String?
):BaseResponse()