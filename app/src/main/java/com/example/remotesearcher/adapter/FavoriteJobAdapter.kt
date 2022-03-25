package com.example.remotesearcher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.remotesearcher.databinding.JobLayoutAdapterBinding
import com.example.remotesearcher.fragments.MianFragmentDirections
import com.example.remotesearcher.model.FavoriteJobsItem
import com.example.remotesearcher.model.JobsItem

class FavoriteJobAdapter(private val onItemClick:OnItemClick) :RecyclerView.Adapter<FavoriteJobAdapter.FavoriteJobViewHolder>(){

    private var binding :JobLayoutAdapterBinding?=null

    inner class FavoriteJobViewHolder(itemBinding:JobLayoutAdapterBinding):RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback= object :DiffUtil.ItemCallback<FavoriteJobsItem>(){
        override fun areItemsTheSame(
            oldItem: FavoriteJobsItem,
            newItem: FavoriteJobsItem
        ): Boolean {
            return newItem.url==oldItem.url
        }

        override fun areContentsTheSame(
            oldItem: FavoriteJobsItem,
            newItem: FavoriteJobsItem
        ): Boolean {
            return newItem==oldItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteJobViewHolder {
        binding= JobLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavoriteJobViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: FavoriteJobViewHolder, position: Int) {
        val currentJob = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(currentJob.companyLogo)
                .into(binding?.ivCompanyLogo!!)
            binding?.tvCompanyName?.text=currentJob.companyName
            binding?.tvJobTitle?.text=currentJob.title
            binding?.tvJobType?.text=currentJob.jobType
            binding?.tvJobLocation?.text=currentJob.candidateRequiredLocation
            binding?.ibDelete?.visibility= View.VISIBLE

            val dateType= currentJob.publicationDate?.split("T")
            binding?.tvDate?.text=dateType?.get(0)

        }.setOnClickListener { view->
            val tags= arrayListOf<String>()
            val jobItem = JobsItem(
                currentJob.jobType,
                currentJob.candidateRequiredLocation,currentJob.companyLogo,currentJob.companyName,
                currentJob.publicationDate,currentJob.description,currentJob.jobId,currentJob.title,
                currentJob.category,currentJob.salary,currentJob.url,tags,currentJob.companyLogoUrl
            )

            val direction= MianFragmentDirections.actionMianFragmentToJobDetailsViewsFragment(jobItem)
            view.findNavController().navigate(direction)

        }
        if (onItemClick!=null){
            binding?.ibDelete?.setOnClickListener {
                onItemClick.onItemClickListenr(currentJob,position,binding?.ibDelete!!)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    interface OnItemClick{
        fun onItemClickListenr(jobsItem: FavoriteJobsItem,position: Int,view: View)
    }
}








