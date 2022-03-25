package com.example.remotesearcher.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.remotesearcher.Utils
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName ="fav_tab")
data class FavoriteJobsItem(
	@PrimaryKey(autoGenerate = true)
	var id : Int,
	val jobType: String? = null,
	val candidateRequiredLocation: String? = null,
	val companyLogo: String? = null,
	val companyName: String? = null,
	val publicationDate: String? = null,
	val description: String? = null,
	val jobId: Int? = null,
	val title: String? = null,
	val category: String? = null,
	val salary: String? = null,
	val url: String? = null,
	val companyLogoUrl: String? = null
)