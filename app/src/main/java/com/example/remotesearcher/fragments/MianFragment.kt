package com.example.remotesearcher.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.remotesearcher.R
import com.example.remotesearcher.databinding.FragmentMianBinding
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class MianFragment : Fragment(R.layout.fragment_mian) {

    private var _binding : FragmentMianBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentMianBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSmartTabBar()

    }

    private fun setUpSmartTabBar() {
        val adapterPager= FragmentPagerItemAdapter(childFragmentManager,
            FragmentPagerItems.with(activity)
                .add("Jobs", RemoteJobFragment::class.java)
                .add("Search",SearchJobFragment::class.java)
                .add("Saved Jobs", SavedJobFragment::class.java)
                .create()

        )
        binding.viewpager.adapter= adapterPager
        binding.viewpagertab.setViewPager(binding.viewpager)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding= null
    }

}