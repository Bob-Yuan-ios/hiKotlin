package com.example.dataRequests.notice

import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeApi {

    @GET("/api/app/version/queryVersion")
    suspend fun getNoticeList(
        @Query("plat_type_key") plat_type_key:String ,
        @Query("company_id") company_id:String ,
        @Query("app_id")  app_id:String,
        @Query("lang")  lang:String
    ): NoticeResponse
}