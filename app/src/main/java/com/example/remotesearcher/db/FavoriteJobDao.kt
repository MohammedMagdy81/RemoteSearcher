package com.example.remotesearcher.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.remotesearcher.model.FavoriteJobsItem

@Dao
interface FavoriteJobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteJob(job:FavoriteJobsItem)

    @Query("select * from fav_tab Order by id Desc")
   fun getAllFavoriteJob():LiveData<List<FavoriteJobsItem>>

    @Delete
    suspend fun deleteFavoriteJob(job:FavoriteJobsItem)
}