package com.example.remotesearcher.api

import android.util.Log
import com.example.remotesearcher.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

        private val retrofit by lazy {
            //val loggingInterceptor= HttpLoggingInterceptor()
           // loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY

            //val client= OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

            Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(client)
                .build()
        }

        val apiService by lazy {
            retrofit.create(RemoteJobeWebService::class.java)
        }

    }

}