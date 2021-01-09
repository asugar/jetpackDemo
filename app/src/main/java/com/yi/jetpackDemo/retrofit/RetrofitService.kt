package com.yi.jetpackDemo.retrofit

import com.yi.jetpackDemo.retrofit.entiry.AppIndexDataResp
import com.yi.jetpackDemo.retrofit.entiry.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("app/indexApp/getAppIndexData")
    fun getAppIndexData(@Query("insId") insId: String): Observable<Result<AppIndexDataResp>>

//    @POST("/")
//    fun login(@Body login: LoginReq): Observable<Result<LoginResp>>
}