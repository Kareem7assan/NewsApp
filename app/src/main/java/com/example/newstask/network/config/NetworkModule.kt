package com.example.newstask.network.config

import com.example.newstask.BuildConfig
import com.example.newstask.network.apis.NewsApi
import retrofit2.Retrofit




val newsGateWay by lazy { retrofit.create(NewsApi::class.java) }


private val retrofit: Retrofit = createNetworkClient(BuildConfig.BASE_URL, BuildConfig.DEBUG)

