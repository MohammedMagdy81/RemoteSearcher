package com.example.remotesearcher.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobsItem(

	@field:SerializedName("job_type")
	val jobType: String? = null,

	@field:SerializedName("candidate_required_location")
	val candidateRequiredLocation: String? = null,

	@field:SerializedName("company_logo")
	val companyLogo: String? = null,

	@field:SerializedName("company_name")
	val companyName: String? = null,

	@field:SerializedName("publication_date")
	val publicationDate: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("salary")
	val salary: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null,

	@field:SerializedName("company_logo_url")
	val companyLogoUrl: String? = null
): Parcelable