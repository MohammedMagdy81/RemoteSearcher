package com.example.remotesearcher.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remotesearcher.repo.RemoteJobRepository

class RemoteJobViewModelFactory(val app:Application,
                                private val remoteJobRepository: RemoteJobRepository): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RemoteJobViewModel(app,remoteJobRepository) as T

    }


}