package com.example.remotesearcher

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

object Utils {
    const val BASE_URL= "https://remotive.io/api/"
    const val TAG= "Remote Researcher  . . "
    const val DATA_BASE_NAME= "DB_name "

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkInternetConnection(context: Context):Boolean{
        val connectivityManager= context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork?:return false
        val activeNetwork= connectivityManager.getNetworkCapabilities(network)?:return false

        return when{
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
            else-> return false
        }



    }

}