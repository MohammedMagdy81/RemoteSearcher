package com.example.remotesearcher.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.remotesearcher.MainActivity
import com.example.remotesearcher.R
import com.example.remotesearcher.adapter.FavoriteJobAdapter
import com.example.remotesearcher.databinding.FragmentSavedJobBinding
import com.example.remotesearcher.model.FavoriteJobsItem
import com.example.remotesearcher.viewmodel.RemoteJobViewModel

class SavedJobFragment : Fragment(R.layout.fragment_saved_job) , FavoriteJobAdapter.OnItemClick{

    private var _binding: FragmentSavedJobBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var favoriteJobAdapter: FavoriteJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSavedJobBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).remoteViewModel
        setUpRecyclerViews()

    }

    private fun setUpRecyclerViews() {
        favoriteJobAdapter= FavoriteJobAdapter(this)

        binding.favRecyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(object : DividerItemDecoration(activity, LinearLayout.HORIZONTAL) {})
            adapter = favoriteJobAdapter
        }
        observeInViewModel()
    }

    private fun observeInViewModel() {
        viewModel.getAllFavoriteJobs().observe(viewLifecycleOwner) {
            favoriteJobAdapter.differ.submitList(it)
            updateUi(it)

        }
    }

    private fun updateUi(favJob: List<FavoriteJobsItem>?) {
        if (favJob!!.isNotEmpty()){
            binding.favRecyclerView.visibility= View.VISIBLE
            binding.textNoSavedJob.visibility= View.GONE
        }else{
            binding.favRecyclerView.visibility= View.GONE
            binding.textNoSavedJob.visibility= View.VISIBLE
        }
    }

    override fun onItemClickListenr(jobsItem: FavoriteJobsItem, position: Int, view: View) {
        deleteJob(jobsItem)
    }

    private fun deleteJob(jobsItem: FavoriteJobsItem) {
        AlertDialog.Builder(activity).apply{
            setTitle("DELETE jOB ")
            setMessage("Are You Want To DElete This Job ? ")

            setPositiveButton("DELETE ") { dialoge, i ->
                viewModel.deleteJob(jobsItem)
                Toast.makeText(activity,"Job Deleted Successfully . . ", Toast.LENGTH_LONG).show() }
            setNegativeButton("CANCEL"){dialoge, i ->
                dialoge.dismiss() }
        }.create().show()
    }


}