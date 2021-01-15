package com.example.karaokeqr.data.network

import com.example.karaokeqr.data.model.responses.LoginResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse

    @POST("logout")
    suspend fun logout(): ResponseBody
}