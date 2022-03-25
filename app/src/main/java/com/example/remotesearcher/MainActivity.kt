package com.example.remotesearcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remotesearcher.databinding.ActivityMainBinding
import com.example.remotesearcher.db.FavoriteJobDatabase
import com.example.remotesearcher.repo.RemoteJobRepository
import com.example.remotesearcher.viewmodel.RemoteJobViewModel
import com.example.remotesearcher.viewmodel.RemoteJobViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var remoteViewModel: RemoteJobViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
       // var viewModel= ViewModelProvider(this).get(RemoteJobViewModel::class.java)
        setContentView(binding.root)
        setSupportActionBar(binding.toolBar)
        supportActionBar?.title=""
        setUpViewModel()

    }

    private fun setUpViewModel() {
       val remoteJobRepository= RemoteJobRepository(FavoriteJobDatabase(this))
        val viewModelFactory = RemoteJobViewModelFactory(application, remoteJobRepository)
        remoteViewModel= ViewModelProvider(this, viewModelFactory).get(RemoteJobViewModel::class.java)
    }
}