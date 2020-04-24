package net.ihaha.sunny.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.lifecycle.LiveData

/**
 * Created by mkwon on 29/08/2019.
 */
class NetworkConnection(context: Context) : LiveData<Boolean>() {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    enum class NetworkType {
        NONE, WIFI, MOBILE, ETC
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network?) {
            // TODO: Logger
            postValue(checkNetworkType() != NetworkType.NONE)
        }

        override fun onLost(network: Network?) {
            // TODO: Logger
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()

        val isConnected = connectivityManager.activeNetworkInfo?.isConnected ?: false
        // TODO: Logger - isConnected

        postValue(isConnected)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val networkBuilder = NetworkRequest.Builder().build()
            connectivityManager.registerNetworkCallback(networkBuilder, networkCallback)
        }
    }

    override fun onInactive() {
        super.onInactive()
        // TODO: Logger
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun checkNetworkType(): NetworkType {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkType.WIFI
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkType.MOBILE
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> NetworkType.ETC
                    else -> NetworkType.NONE
                }
            } ?: NetworkType.NONE
        } else {
            connectivityManager.activeNetworkInfo?.run {
                when (type) {
                    ConnectivityManager.TYPE_WIFI -> NetworkType.WIFI
                    ConnectivityManager.TYPE_MOBILE -> NetworkType.MOBILE
                    else -> NetworkType.NONE
                }
            } ?: NetworkType.NONE
        }
    }

}