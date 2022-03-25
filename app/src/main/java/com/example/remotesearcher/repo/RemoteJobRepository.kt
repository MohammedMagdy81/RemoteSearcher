package com.example.remotesearcher.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.remotesearcher.Utils
import com.example.remotesearcher.api.RemoteJobeWebService
import com.example.remotesearcher.api.RetrofitInstance
import com.example.remotesearcher.db.FavoriteJobDatabase
import com.example.remotesearcher.model.FavoriteJobsItem
import com.example.remotesearcher.model.RemoteJobResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteJobRepository(private val instance: FavoriteJobDatabase) {

    private val remoteJobeWebService:RemoteJobeWebService= RetrofitInstance.apiService

    private val remoteJobLiveData:MutableLiveData<RemoteJobResponse> = MutableLiveData()
    private val searchJobLiveData:MutableLiveData<RemoteJobResponse> = MutableLiveData()

    init {
        getRemoteJobResponse()
    }

    fun getRemoteJobResponse(){
        remoteJobeWebService.getRemoteJobResponse().enqueue(object : Callback<RemoteJobResponse>{
            override fun onResponse(
                call: Call<RemoteJobResponse>,
                response: Response<RemoteJobResponse>
            ) {
                remoteJobLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<RemoteJobResponse>, t: Throwable) {
               Log.e(Utils.TAG,t.localizedMessage)
            }

        })
    }

    fun getSearchJobResponse(query:String?){
       remoteJobeWebService.searchJob(query).enqueue(object :Callback<RemoteJobResponse>{
           override fun onResponse(
               call: Call<RemoteJobResponse>,
               response: Response<RemoteJobResponse>
           ) {
               searchJobLiveData.postValue(response.body())
           }

           override fun onFailure(call: Call<RemoteJobResponse>, t: Throwable) {
               Log.e(Utils.TAG,t.localizedMessage)
           }

       })
    }

    fun getRemoteJobResults(): LiveData<RemoteJobResponse>{
        return remoteJobLiveData
    }

    fun getSearchJobResults(): LiveData<RemoteJobResponse>{
        return searchJobLiveData
    }




    suspend fun deleteJob(job:FavoriteJobsItem) = instance.getFavoriteDao().deleteFavoriteJob(job)
    suspend fun addFavoriteJob(job:FavoriteJobsItem) = instance.getFavoriteDao().addFavoriteJob(job)
     fun getAllFavoriteJobs() = instance.getFavoriteDao().getAllFavoriteJob()



}