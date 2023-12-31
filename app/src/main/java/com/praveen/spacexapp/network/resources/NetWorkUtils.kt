package com.praveen.spacexapp.network.resources

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun Context.isOnline(): Boolean {
    var result = false
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.activeNetworkInfo?.run {
            result = when (type) {
                ConnectivityManager.TYPE_WIFI -> true
                ConnectivityManager.TYPE_MOBILE -> true
                ConnectivityManager.TYPE_ETHERNET -> true
                else -> false
            }

        }
    }
    return result
}

/**
 * No Internet Connection Exception class
 * @param message string value for network error message
 */
class NoInternetConnection(message: String) : Exception(message)
object BadRequestException : Exception()
object UnAuthorizedException : Exception()
object InternalServerException : Exception()
object UnKnownException : Exception()
