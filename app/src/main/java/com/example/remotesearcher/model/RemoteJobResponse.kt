package com.example.remotesearcher.model

import com.google.gson.annotations.SerializedName

data class RemoteJobResponse(

	@field:SerializedName("job-count")
	val jobCount: Int? = null,

	@field:SerializedName("jobs")
	val jobs: List<JobsItem?>? = null,

	@field:SerializedName("0-legal-notice")
	val jsonMember0LegalNotice: String? = null
)