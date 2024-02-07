package com.hyak4j.cb.tarvel.taipei.model.news

import com.hyak4j.cb.tarvel.taipei.model.OpenAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepository {
    val openAPIService by lazy {
        OpenAPI.openAPIService
    }

    // 專職向OpenAPI取得最新消息
    suspend fun getNews(): List<Data> {
        return withContext(Dispatchers.IO) {
            val response = openAPIService.getNews(page = 1).execute()
            response.body()?.data?: listOf()
        }
    }
}