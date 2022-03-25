package com.example.remotesearcher.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.remotesearcher.MainActivity
import com.example.remotesearcher.R
import com.example.remotesearcher.adapter.RemoteJobAdapter
import com.example.remotesearcher.databinding.FragmentSearchJobBinding
import com.example.remotesearcher.model.JobsItem
import com.example.remotesearcher.viewmodel.RemoteJobViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SearchJobFragment : Fragment(R.layout.fragment_search_job) {
    private var _binding: FragmentSearchJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var searchAdapter: RemoteJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchJobBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).remoteViewModel
        searchJob()
        setUpRecyclerView()

    }

    private fun searchJob() {
        var job: Job? = null

        binding?.etSearch?.addTextChangedListener { text ->
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                if (text?.toString()!!.isNotEmpty()) {
                    viewModel.searchJob(text.toString())
                }
            }
        }

    }


    private fun setUpRecyclerView() {
    searchAdapter= RemoteJobAdapter()
        binding.rvSearchJob.apply {
            setHasFixedSize(true)
            addItemDecoration(object : DividerItemDecoration(activity, LinearLayout.HORIZONTAL) {})
            adapter= searchAdapter
        }
        observeToViewModel()

    }

    private fun observeToViewModel() {
        viewModel.searchJobResult().observe(viewLifecycleOwner) {
            searchAdapter.differ.submitList(it.jobs)
        }
    }


}









