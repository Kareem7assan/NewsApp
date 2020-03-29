package com.example.newstask.repositories

import com.example.newstask.network.model.news_model.NewsModel
import io.reactivex.Observable
import retrofit2.Response


val newsRepository by lazy { NewsRepositoryImp() }

interface NewsRepository {

    fun getArticlesByAssociated(): Observable<Response<NewsModel>>
    fun getArticlesByNextWeb(): Observable<Response<NewsModel>>
   // fun getAllArticles():Observable<Response<NewsModel>>

}