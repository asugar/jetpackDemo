package com.yi.jetpackDemo.retrofit

import com.yi.jetpackDemo.MyApplication
import com.yi.jetpackDemo.retrofit.entity.AppIndexDataResp
import com.yi.jetpackDemo.retrofit.entity.PerformanceLog
import com.yi.jetpackDemo.retrofit.entity.Result
import com.yi.jetpackDemo.retrofit.entity.TrainMonth
import com.yi.jetpackDemo.retrofit.manager.CACHE_QUERY
import com.yi.jetpackDemo.retrofit.manager.HOST_KEY
import io.reactivex.Observable
import retrofit2.http.*

interface RetrofitService {

    @GET("app/indexApp/getAppIndexData")
    fun getAppIndexData(
        @Query("insId") insId: String,
        @Query(CACHE_QUERY) cache: Boolean = false
    ): Observable<Result<AppIndexDataResp>>

    @Headers("$HOST_KEY:${MyApplication.TRAIN_HOST}")
    @GET("thewolverine/trainCalendar/getMonthListForApp")
    fun getAppWithHosts(): Observable<Result<List<TrainMonth>>>

    /**
     * 使用@Url
     */
    @GET
    fun getAppWithUrl(
        @Url url: String,
        @Query("") insId: String?
    ): Observable<Result<List<TrainMonth>>>


    /**
     * 此方法处理h5 get请求
     */
    @GET
    fun refreshH5Get(
        @Url url: String,
        @QueryMap map: Map<String, String>
    ): Observable<Result<Any>>

    /**
     * 此方法处理h5 post请求
     */
    @FormUrlEncoded
    @POST
    fun refreshH5Post(
        @Url url: String,
        @FieldMap params: Map<String, String>
    ): Observable<Result<Any>>

    @POST
    fun uploadLogPost(
        @Url url: String,
        @Body body: PerformanceLog
    ): Observable<Result<Any>>

    @POST
    fun uploadLogPostWithString(
        @Url url: String,
        @Body body: String
    ): Observable<Result<Any>>

    @GET("app/indexApp/getAppIndexData")
    suspend fun getAppIndexData2(
        @Query("insId") insId: String,
        @Query(CACHE_QUERY) cache: Boolean = false
    ): Result<AppIndexDataResp>

//    @POST("/")
//    fun login(@Body login: LoginReq): Observable<Result<LoginResp>>
}