package com.ctech.common.context

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat


fun Context.inflate(resource: Int, root: ViewGroup?, attachToRoot: Boolean): View =
        LayoutInflater.from(this).inflate(resource, root, attachToRoot)

fun Context.hasPermission(perm: String): Boolean {
    return ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
