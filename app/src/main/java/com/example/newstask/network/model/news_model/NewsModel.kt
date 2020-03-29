package com.example.newstask.network.model.news_model

import com.example.newstask.network.model.BaseResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsModel(
    @SerializedName("articles")
    val articles: List<Article?>?,
    @SerializedName("sortBy")
    val sortBy: String?,
    @SerializedName("source")
    val source: String?
): BaseResponse(),Serializable