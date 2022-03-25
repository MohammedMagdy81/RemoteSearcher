package com.example.remotesearcher.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.remotesearcher.Utils
import com.example.remotesearcher.model.FavoriteJobsItem

@Database(entities = [FavoriteJobsItem::class], version = 1,exportSchema = false)
abstract class FavoriteJobDatabase : RoomDatabase() {

    abstract fun getFavoriteDao() : FavoriteJobDao

    companion object{

        @Volatile
        private var instanceJobDatabase: FavoriteJobDatabase?=null
        private val LOCK= Any()

        operator fun invoke(context: Context)= instanceJobDatabase?: synchronized(LOCK){
            instanceJobDatabase?:getInstance(context).also {
                instanceJobDatabase=it
            }
        }



        fun getInstance(context: Context):FavoriteJobDatabase{
            if (instanceJobDatabase==null){
                instanceJobDatabase= Room.databaseBuilder(context,
                    FavoriteJobDatabase::class.java,
                    Utils.DATA_BASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instanceJobDatabase!!
        }




    }
}