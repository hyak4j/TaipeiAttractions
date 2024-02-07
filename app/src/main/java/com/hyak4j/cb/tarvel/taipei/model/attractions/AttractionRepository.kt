package com.hyak4j.cb.tarvel.taipei.model.attractions

import com.hyak4j.cb.tarvel.taipei.model.OpenAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AttractionRepository {
    val openAPIService by lazy {
        OpenAPI.openAPIService
    }

    // 專職向OpenAPI取得遊憩景點
    suspend fun getAttractions(): AttractionResponse {
        return withContext(Dispatchers.IO) {
            val response = openAPIService.getAllAttractions(page = 1).execute()
            val attractions = response.body()?.data?: listOf()
            val total = response.body()?.total?: 0
            AttractionResponse(attractions, total)
        }
    }
}