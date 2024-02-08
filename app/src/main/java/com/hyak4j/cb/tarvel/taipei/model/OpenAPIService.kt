package com.hyak4j.cb.tarvel.taipei.model


import com.hyak4j.cb.tarvel.taipei.model.attractions.Attractions
import com.hyak4j.cb.tarvel.taipei.model.news.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface OpenAPIService {
    /*
        Swagger: https://www.travel.taipei/open-api/swagger/ui/index
     */
    // 取得所有遊憩景點
    @GET("{lang}/Attractions/All")
    fun getAllAttractions(
        @Path("lang") lang: String,
        @Header("Accept") acceptHeader: String = "application/json",
        @Query("nlat") nlat: Double? = null, // 查詢附近景點，經緯度(北緯) WGS84
        @Query("elong") elong: Double? = null, // 查詢附近景點，經緯度(東經) WGS84
        @Query("page") page: Int // 頁碼。(每次回應30筆資料)
    ): Call<Attractions>

    // 活動資訊-最新消息
    @GET("{lang}/Events/News")
    fun getNews(
        @Path("lang") lang: String,
        @Header("Accept") acceptHeader: String = "application/json",
        @Query("page") page: Int
    ): Call<News>
}