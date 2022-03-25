package com.example.remotesearcher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.remotesearcher.MainActivity
import com.example.remotesearcher.R
import com.example.remotesearcher.adapter.RemoteJobAdapter
import com.example.remotesearcher.databinding.FragmentRemoteJobBinding
import com.example.remotesearcher.viewmodel.RemoteJobViewModel

class RemoteJobFragment : Fragment(R.layout.fragment_remote_job) {

    private var _binding : FragmentRemoteJobBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel:RemoteJobViewModel
    private lateinit var remoteJobAdapter: RemoteJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= (activity as MainActivity).remoteViewModel
        // Inflate the layout for this fragment
        _binding= FragmentRemoteJobBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()

    }

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

    private fun fetchingData() {
        viewModel.getRemoteJobResults().observe(viewLifecycleOwner) { remoteJobResults ->
            if (remoteJobResults != null) {
                remoteJobAdapter.differ.submitList(remoteJobResults.jobs)
            }

        }
    }

 }