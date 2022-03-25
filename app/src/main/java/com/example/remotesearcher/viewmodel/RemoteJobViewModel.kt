package com.example.remotesearcher.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.remotesearcher.model.FavoriteJobsItem
import com.example.remotesearcher.repo.RemoteJobRepository
import kotlinx.coroutines.launch

class RemoteJobViewModel(val app: Application , private var remoteJobRepository: RemoteJobRepository
): AndroidViewModel(app){

    fun getRemoteJobResults() = remoteJobRepository.getRemoteJobResults()

    fun addFavoriteJob(job:FavoriteJobsItem) = viewModelScope.launch {
        remoteJobRepository.addFavoriteJob(job)
    }

    fun deleteJob(job:FavoriteJobsItem) = viewModelScope.launch {
        remoteJobRepository.deleteJob(job)
    }

    fun getAllFavoriteJobs() = remoteJobRepository.getAllFavoriteJobs()

    fun searchJob(query:String?) = remoteJobRepository.getSearchJobResponse(query)

    fun searchJobResult()= remoteJobRepository.getSearchJobResults()

}