package com.example.newstask.repositories

import com.example.newstask.network.apis.NewsApi
import com.example.newstask.network.config.newsGateWay
import com.example.newstask.network.model.news_model.NewsModel
import io.reactivex.Observable
import retrofit2.Response

class NewsRepositoryImp(
    private val newsApi: NewsApi = newsGateWay
  //  private val preferences: PreferencesGateway = preferencesGateway
) : NewsRepository {




    override fun getArticlesByAssociated(): Observable<Response<NewsModel>> {
        return newsApi.news("associated-press")
    }

    override fun getArticlesByNextWeb(): Observable<Response<NewsModel>> {
        return newsApi.news("the-next-web")
    }




}