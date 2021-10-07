package com.pasa.parimca.appp.Utilities2oh7ld

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment
import android.util.Base64
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun Context.checkPermission2oh7ld () {
    Dexter.withContext(this)
        .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
        .withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                p1: PermissionToken?
            ) {

            }
        }).check()
}

fun Context.createTmpFile2oh7ld (): File {
    val date2oh7ld = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        .format(Date())
    val fileDir2oh7ld = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("TMP${date2oh7ld}_2oh7ld", ".jpg", fileDir2oh7ld)
}

fun String.decodeString2oh7ld () = String(Base64.decode(this, Base64.DEFAULT))

fun Context.internetChecker2oh7ld (): Boolean {
    val connectivityManager2oh7ld = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val networkCapabilities2oh7ld = connectivityManager2oh7ld.getNetworkCapabilities(connectivityManager2oh7ld.activeNetwork) ?: return false
        return networkCapabilities2oh7ld.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        for (network2oh7ld in connectivityManager2oh7ld.allNetworks) {
            connectivityManager2oh7ld.getNetworkInfo(network2oh7ld)?.let {
                if (it.isConnected) return true
            }
        }
        return false
    }
}