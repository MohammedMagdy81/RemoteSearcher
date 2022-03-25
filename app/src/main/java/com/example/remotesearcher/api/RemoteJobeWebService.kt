package com.example.remotesearcher.api

import com.example.remotesearcher.model.RemoteJobResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteJobeWebService {

    @GET("remote-jobs?limit=5")
    fun getRemoteJobResponse(): Call<RemoteJobResponse>

    @GET("remote-jobs")
    fun searchJob(@Query("search") query : String?): Call<RemoteJobResponse>
}