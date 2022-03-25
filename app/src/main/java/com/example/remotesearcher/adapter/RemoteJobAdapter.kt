package com.example.remotesearcher.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.remotesearcher.databinding.JobLayoutAdapterBinding
import com.example.remotesearcher.fragments.MianFragmentDirections
import com.example.remotesearcher.model.JobsItem

class RemoteJobAdapter :RecyclerView.Adapter<RemoteJobAdapter.RemoteJoViewHolder>(){

    private var binding :JobLayoutAdapterBinding?=null

    inner class RemoteJoViewHolder(itemBinding:JobLayoutAdapterBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback= object :DiffUtil.ItemCallback<JobsItem>(){
        override fun areItemsTheSame(oldItem: JobsItem, newItem: JobsItem): Boolean {
            return newItem.url==oldItem.url
        }

        override fun areContentsTheSame(oldItem: JobsItem, newItem: JobsItem): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteJoViewHolder {
        binding= JobLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RemoteJoViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RemoteJoViewHolder, position: Int) {
        val currentJob = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(currentJob.companyLogo)
                .into(binding?.ivCompanyLogo!!)

            binding?.tvCompanyName?.text=currentJob.companyName
            binding?.tvJobTitle?.text=currentJob.title
            binding?.tvJobType?.text=currentJob.jobType
            binding?.tvJobLocation?.text=currentJob.candidateRequiredLocation

            val dateType= currentJob.publicationDate?.split("T")
            binding?.tvDate?.text=dateType?.get(0)
        }.setOnClickListener { view->
            val direction= MianFragmentDirections.actionMianFragmentToJobDetailsViewsFragment(currentJob)
            view.findNavController().navigate(direction)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}