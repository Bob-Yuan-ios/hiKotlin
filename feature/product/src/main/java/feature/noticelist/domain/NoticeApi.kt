package feature.noticelist.domain

import feature.noticelist.data.NoticeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeApi {

    @GET("/api/app/version/queryVersion")
    suspend fun getNoticeList(
        @Query("plat_type_key") platTypeKey:String,
        @Query("company_id") companyId:String,
        @Query("app_id")  appId:String,
        @Query("lang")  lang:String
    ): NoticeResponse
}