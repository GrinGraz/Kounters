package cl.getapps.kounters.base

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import kotlinx.coroutines.*


fun ConnectivityManager?.areActiveNetwork(): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this?.run {
            this.getNetworkCapabilities(this.activeNetwork)?.run {
                if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                }
            }
        }
    } else {
        this?.run {
            this.activeNetworkInfo?.run {
                if (type == ConnectivityManager.TYPE_WIFI) {
                    return true
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    return true
                }
            }
        }
    }
    return false
}

fun showWithDelay(delayInMillis: Long, block: () -> Unit) {
    CoroutineScope(Dispatchers.Main).launch {
        delay(delayInMillis)
        block()
    }
}
