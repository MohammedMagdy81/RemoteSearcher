package com.example.remotesearcher.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.remotesearcher.MainActivity
import com.example.remotesearcher.R
import com.example.remotesearcher.Utils
import com.example.remotesearcher.adapter.RemoteJobAdapter
import com.example.remotesearcher.databinding.FragmentRemoteJobBinding
import com.example.remotesearcher.viewmodel.RemoteJobViewModel

class RemoteJobFragment : Fragment(R.layout.fragment_remote_job),SwipeRefreshLayout.OnRefreshListener {

    private var _binding : FragmentRemoteJobBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel:RemoteJobViewModel
    private lateinit var remoteJobAdapter: RemoteJobAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= (activity as MainActivity).remoteViewModel

        // Inflate the layout for this fragment
        _binding= FragmentRemoteJobBinding.inflate(inflater,container,false)

        swipeRefreshLayout=binding.swipeContainer
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN,Color.RED,Color.BLUE)
        swipeRefreshLayout.post {

            swipeRefreshLayout.isRefreshing=true
            setUpRecyclerView()
        }

        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setUpRecyclerView() {
        remoteJobAdapter= RemoteJobAdapter()

        binding.rvRemotJob.apply {
            setHasFixedSize(true)
            addItemDecoration(object :DividerItemDecoration(activity,LinearLayout.HORIZONTAL){})
            //adapter=remoteJobAdapter
            binding.rvRemotJob.adapter=remoteJobAdapter

        }
        fetchingData()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun fetchingData() {
        if (Utils.checkInternetConnection(requireContext())){
            viewModel.getRemoteJobResults().observe(viewLifecycleOwner) { remoteJobResults ->
                if (remoteJobResults != null) {
                    remoteJobAdapter.differ.submitList(remoteJobResults.jobs)
                    swipeRefreshLayout.isRefreshing=false
                }
            }

        }else{

            binding.textNoInternet.visibility= View.VISIBLE
            swipeRefreshLayout.isRefreshing=false

        }

      }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRefresh() {
        setUpRecyclerView()
    }


}