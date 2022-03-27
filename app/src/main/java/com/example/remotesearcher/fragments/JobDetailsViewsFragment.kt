package com.example.remotesearcher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.remotesearcher.MainActivity
import com.example.remotesearcher.R
import com.example.remotesearcher.databinding.FragmentJobDetailsViewsBinding
import com.example.remotesearcher.db.FavoriteJobDatabase
import com.example.remotesearcher.model.FavoriteJobsItem
import com.example.remotesearcher.model.JobsItem
import com.example.remotesearcher.viewmodel.RemoteJobViewModel
import com.google.android.material.snackbar.Snackbar

class JobDetailsViewsFragment : Fragment(R.layout.fragment_job_details_views) {

    private var _binding:FragmentJobDetailsViewsBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: RemoteJobViewModel

    lateinit var currentjob:JobsItem
    private val args :JobDetailsViewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentJobDetailsViewsBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as MainActivity).remoteViewModel
        currentjob= args.job
        setUpWebView()
        binding.fab.setOnClickListener {
            addFavoriteJob(view)
        }
    }

    private fun addFavoriteJob(view: View) {
        val favoJob= FavoriteJobsItem(0,currentjob.jobType,
            currentjob.candidateRequiredLocation,currentjob.companyLogo,currentjob.companyName,
            currentjob.publicationDate,currentjob.description,currentjob.id,currentjob.title,
            currentjob.category,currentjob.salary,currentjob.url,currentjob.companyLogoUrl)
        viewModel.addFavoriteJob(favoJob)
        Snackbar.make(view,"Job Saved Successfully . . " , Snackbar.LENGTH_LONG).show()
    }

    private fun setUpWebView() {
        binding.webView?.apply {

            webViewClient= WebViewClient()
            currentjob.url?.let { loadUrl(it) }

        }

        val setting = binding.webView.settings
        setUpUrlClicking(setting)

    }

    private fun setUpUrlClicking(setting: WebSettings) {
        setting.apply {
            javaScriptEnabled=true
            setAppCacheEnabled(true)
            cacheMode=WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls= false
            displayZoomControls=false
            textZoom=100
            blockNetworkImage=false
            loadsImagesAutomatically=true
        }
    }


}