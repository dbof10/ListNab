package com.ctech.memoir.core.fragment

import android.content.Intent

interface ActivityResultReceiver {
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}